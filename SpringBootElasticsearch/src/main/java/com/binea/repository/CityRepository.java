package com.binea.repository;

import com.binea.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by binea
 * Date: 31/10/2017
 * TIME: 10:48 PM
 */

@Repository
public interface CityRepository extends ElasticsearchRepository<City, Long> {

}
