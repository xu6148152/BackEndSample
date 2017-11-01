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

    private final Integer PAGE_SIZE = 12;

    private final Integer DEFAULT_PAGE_NUMBER = 0;

    String SCORE_MODE_SUM = "sum";

    Float MIN_SCORE = 10.0F;

    @Autowired
    CityRepository cityRepository;

    Integer pageNumber = DEFAULT_PAGE_NUMBER;
    Integer pageSize = PAGE_SIZE;

    Pageable pageable = new PageRequest(pageNumber, pageSize);

    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {

        if (pageSize == null || pageSize == 0) {
            pageSize = PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n");

//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery().add(QueryBuilders.boolQuery().should(QueryBuilders.
//                matchQuery("cityname", searchContent)), ScoreFunctionBuilders.weightFactorFunction(1000))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100));
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery
//                (functionScoreQueryBuilder).build();

        SearchQuery searchQuery = getCitySearchQuery(pageNumber, pageSize, searchContent);
        LOGGER.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL = \n " + searchQuery.getQuery()
                .toString());

        Page<City> searchPageResult = cityRepository.search(searchQuery);
        return searchPageResult.getContent();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityRepository.findByDescriptionOrScore(description, score);
    }

    @Override
    public List<City> findByDescription(String description) {
        return cityRepository.findByDescription(description, pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionNot(String description) {
        return cityRepository.findByDescriptionNot(description, pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionLike(String description) {
        return cityRepository.findByDescriptionLike(description, pageable).getContent();
    }

    private SearchQuery getCitySearchQuery(Integer pageNumber, Integer pageSize, String searchContent) {

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery().add(QueryBuilders
                .matchPhrasePrefixQuery("name", searchContent), ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhrasePrefixQuery("description", searchContent), ScoreFunctionBuilders
                        .weightFactorFunction(500)).scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);

        Pageable pageable = new PageRequest(pageNumber, pageSize);

        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }
}
