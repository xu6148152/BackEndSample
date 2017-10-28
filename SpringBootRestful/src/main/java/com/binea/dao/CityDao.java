package com.binea.dao;

import com.binea.domain.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 4:12 PM
 */
public interface CityDao {

    List<City> findAllCity();

    City findById(@Param("id") Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
