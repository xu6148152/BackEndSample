package com.binea.controller;

import com.binea.domain.City;
import com.binea.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 4:10 PM
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService mCityService;

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return mCityService.findCityByName(cityName);
    }
}
