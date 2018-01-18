package com.binea.cms.job.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by binea
 * Date: 18/1/2018
 * TIME: 11:10 PM
 */
@Controller
@RequestMapping("/manage/user")
public class UserController {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public String index() {
        return "/user/list";
    }
}
