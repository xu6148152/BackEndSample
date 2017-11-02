package com.binea.dubbo;

import com.binea.domain.City;

/**
 * Created by binea
 * Date: 2/11/2017
 * TIME: 10:46 PM
 */

public interface CityDubboService {
    City findCityByName(String cityName);
}
