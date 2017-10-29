package com.binea.controller;

import com.binea.domain.City;
import com.binea.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 3:45 PM
 */
@Controller
public class CityController {

    @Autowired
    CityService mCityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public String findOneCity(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city", mCityService.findCityById(id));
        return "city";
    }

    @RequestMapping(value = "/api/city/", method = RequestMethod.GET)
    public String findAllCity(Model model) {
        List<City> cityList = mCityService.findAllCity();
        model.addAttribute("cityList", cityList);
        return "cityList";
    }
}
