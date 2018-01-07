package com.binea.service;

import com.binea.domain.City;

import java.util.List;

public interface CityService {

    Long saveCity(City city);

    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);

    List<City> findByDescriptionAndScore(String description, Integer score);

    List<City> findByDescriptionOrScore(String description, Integer score);

    List<City> findByDescription(String description);

    List<City> findByDescriptionNot(String description);

    List<City> findByDescriptionLike(String description);
}