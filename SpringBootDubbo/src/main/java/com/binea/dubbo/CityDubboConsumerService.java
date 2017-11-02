package com.binea.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.binea.domain.City;
import org.springframework.stereotype.Component;

/**
 * Created by binea
 * Date: 2/11/2017
 * TIME: 10:45 PM
 */
@Component
public class CityDubboConsumerService {
    @Reference(version = "1.0.0")
    CityDubboService cityDubboService;

    public void printCity() {
        String cityName = "南安";
        City city = cityDubboService.findCityByName(cityName);
        System.out.print(city.toString());
    }
}
