package com.binea.service.impl;

import com.binea.domain.City;
import com.binea.repository.CityRepository;
import com.binea.service.CityService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by binea
 * Date: 31/10/2017
 * TIME: 10:49 PM
 */

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
        Pageable pageable = new PageRequest(pageNumber, pageSize);

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery().add(QueryBuilders.boolQuery().should(QueryBuilders.
                matchQuery("cityname", searchContent)), ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100));

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery
                (functionScoreQueryBuilder).build();

        LOGGER.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL = \n " + searchQuery.getQuery()
                .toString());

        Page<City> searchPageResult = cityRepository.search(searchQuery);
        return searchPageResult.getContent();
    }
}
