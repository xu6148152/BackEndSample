package com.binea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by binea
 * Date: 11/11/2017
 * TIME: 4:56 PM
 */
@RestController
public class ComputeController {
    private final Logger LOGGER = LoggerFactory.getLogger(ComputeController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public int add(@RequestParam Integer a, @RequestParam Integer b) {
        int result = a + b;
        client.getServices().forEach(id -> client.getInstances(id).forEach(instance -> LOGGER.info(
                "/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result: {}",
                result
        )));
        return result;
    }
}
