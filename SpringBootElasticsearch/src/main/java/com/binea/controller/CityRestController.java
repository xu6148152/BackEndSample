package com.binea.controller;

import com.binea.domain.City;
import com.binea.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by binea
 * Date: 31/10/2017
 * TIME: 11:01 PM
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @RequestMapping(value = "api/city/search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value =
            "pageSize", required = false) Integer pageSize, @RequestParam(value = "searchContent") String
                                         searchContent) {
        return cityService.searchCity(pageNumber, pageSize, searchContent);
    }
}
