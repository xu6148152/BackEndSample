package com.binea.service;

import com.binea.domain.City;

import java.util.List;

public interface CityService {

    Long saveCity(City city);

    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}