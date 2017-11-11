package com.binea.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by binea
 * Date: 11/11/2017
 * TIME: 5:47 PM
 */
@FeignClient("compute-service")
public interface ComputeClient {
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    int add(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b);
}
