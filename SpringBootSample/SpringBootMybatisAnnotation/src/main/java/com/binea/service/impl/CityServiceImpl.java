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
    public City findCityByName(String name) {
        return mCityDao.findByName(name);
    }
}
