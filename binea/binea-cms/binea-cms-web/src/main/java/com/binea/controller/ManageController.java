package com.binea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by binea
 * Date: 10/12/2017
 * TIME: 4:26 PM
 */
@RestController
@RequestMapping("/manage")
public class ManageController {

    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @RequestMapping(value = {"", "/index"})
    public Object index() {
        return "/manage/index";
    }

    @RequestMapping("/login")
    public Object login() {
        return "/manage/login";
    }

}
