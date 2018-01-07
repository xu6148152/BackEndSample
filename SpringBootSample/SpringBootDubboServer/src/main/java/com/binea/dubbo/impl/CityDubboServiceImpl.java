package com.binea.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.binea.domain.City;
import com.binea.dubbo.CityDubboService;

/**
 * Created by binea
 * Date: 2/11/2017
 * TIME: 10:54 PM
 */

@Service(version = "1.0.0")
public class CityDubboServiceImpl implements CityDubboService {
    @Override
    public City findCityByName(String cityName) {
        return new City(1L, 2L, "南安市", "是我的故乡");
    }
}
