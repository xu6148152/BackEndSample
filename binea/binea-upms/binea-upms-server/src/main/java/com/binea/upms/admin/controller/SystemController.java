package com.binea.upms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by binea
 * Date: 25/1/2018
 * TIME: 10:46 PM
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    private static Logger _log = LoggerFactory.getLogger(SystemController.class);

    @RequestMapping("/index")
    public String index() {
        return "/system/index";
    }

}
