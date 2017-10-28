package com.binea.service.impl;

import com.binea.dao.CityDao;
import com.binea.domain.City;
import com.binea.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 4:14 PM
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao mCityDao;

    @Override
    public List<City> findAllCity() {
        return mCityDao.findAllCity();
    }

    @Override
    public City findCityById(Long id) {
        return mCityDao.findById(id);
    }

    @Override
    public Long saveCity(City city) {
        return mCityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return mCityDao.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return mCityDao.deleteCity(id);
    }
}
