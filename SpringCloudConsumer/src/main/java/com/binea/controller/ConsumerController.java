package com.binea.controller;

import com.binea.service.ComputeClient;
import com.binea.hystrix.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by binea
 * Date: 11/11/2017
 * TIME: 5:37 PM
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ComputeClient computeClient;

    @Autowired
    private ComputeService computeService;

    @RequestMapping(value = "/ribbonAdd", method = RequestMethod.GET)
    public String ribbonAdd() {
        return computeService.addService();
    }

    @RequestMapping(value = "/feignAdd", method = RequestMethod.GET)
    public int feignAdd() {
        return computeClient.add(20, 30);
    }
}
