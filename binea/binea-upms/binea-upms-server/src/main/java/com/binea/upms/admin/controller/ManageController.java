package com.binea.upms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by binea
 * Date: 25/1/2018
 * TIME: 10:40 PM
 */
@Controller
@RequestMapping("/manage")
public class ManageController {
    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @RequestMapping(value = {"", "/index"})
    public String index() {
        return "/manage/index";
    }
}
