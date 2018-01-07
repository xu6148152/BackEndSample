package com.binea.controller;

import com.binea.domain.City;
import com.binea.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 4:10 PM
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService mCityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return mCityService.findCityById(id);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public List<City> findAllCity() {
        return mCityService.findAllCity();
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public void createCity(@RequestBody City city) {
        mCityService.saveCity(city);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city) {
        mCityService.updateCity(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public void deleteCity(@PathVariable("id") Long id) {
        mCityService.deleteCity(id);
    }
}
