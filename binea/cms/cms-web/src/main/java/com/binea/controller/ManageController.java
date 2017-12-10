package com.binea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by binea
 * Date: 10/12/2017
 * TIME: 4:26 PM
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @RequestMapping(value = {"", "/index"})
    @ResponseBody
    public Object index() {
        return "/manage/index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login() {
        return "/manage/login";
    }

}
