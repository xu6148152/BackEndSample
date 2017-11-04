package com.binea.web;

import com.binea.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 4:53 PM
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() throws MyException {
        throw new MyException("exception happened");
//        return "Hello, world";
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.addAttribute("host", "http://xu6148152.github.io");
        return "thymeleaf";
    }

    @RequestMapping("/freemarker")
    public String freeMarker(ModelMap map) {
        map.addAttribute("host", "http://xu6148152.github.io");
        return "freemarker";
    }

    @RequestMapping("/velocity")
    public String velocity(ModelMap map) {
        map.addAttribute("host", "http://xu6148152.github.io");
        return "velocity";
    }
}
