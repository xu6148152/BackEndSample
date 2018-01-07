package com.binea.service;

import com.binea.domain.City;

import java.util.List;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 3:46 PM
 */
public interface CityService {
    List<City> findAllCity();

    City findCityById(Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
