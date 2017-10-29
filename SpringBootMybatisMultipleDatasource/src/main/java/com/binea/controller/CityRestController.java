package com.binea.controller;

import com.binea.domain.User;
import com.binea.service.UserService;
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
    private UserService mUserService;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "userName", required = true) String userName) {
        return mUserService.findByName(userName);
    }
}
