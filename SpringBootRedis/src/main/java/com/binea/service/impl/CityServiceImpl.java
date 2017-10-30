package com.binea.service.impl;

import com.binea.dao.CityDao;
import com.binea.domain.City;
import com.binea.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 4:14 PM
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao mCityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<City> findAllCity() {
        return mCityDao.findAllCity();
    }

    @Override
    public City findCityById(Long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        City city;
        if (hasKey) {
            city = operations.get(key);
            LOGGER.info("CityServiceImpl.findCityById(): get city from cache >> " + city.toString());
        } else {
            city = mCityDao.findById(id);
            operations.set(key, city, 10, TimeUnit.SECONDS);
            LOGGER.info("CityServiceImpl.findCityById(): put city into cache >> " + city.toString());
        }
        return city;
    }

    @Override
    public Long saveCity(City city) {
        return mCityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        Long ret = mCityDao.updateCity(city);

        String key = "city_" + city.getId();
        ValueOperations<String, City> ops = redisTemplate.opsForValue();
        ops.set(key, city, 10, TimeUnit.SECONDS);
//        boolean hasKey = redisTemplate.hasKey(key);
//        if (hasKey) {
//            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.updateCity(): delete city from cache >> " + city.toString());
//        }
        return ret;
    }

    @Override
    public Long deleteCity(Long id) {
        Long ret = mCityDao.deleteCity(id);

        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity(): delete city from cache >> " + id);
        }
        return ret;
    }
}
