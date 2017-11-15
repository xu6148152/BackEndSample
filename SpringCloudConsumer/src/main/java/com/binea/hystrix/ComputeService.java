package com.binea.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by binea
 * Date: 12/11/2017
 * TIME: 3:36 PM
 */
@Service
public class ComputeService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService() {
//        ServiceInstance serviceInstance = loadBalancerClient.choose("compute-service");
//        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/add?a=10&b=20";
        String url = "http://COMPUTE-SERVICE/add?a=10&b=20";
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    public String addServiceFallback() {
        return "error";
    }

}
