package com.binea.hystrix;

import com.binea.service.ComputeClient;
import org.springframework.stereotype.Component;

/**
 * Created by binea
 * Date: 12/11/2017
 * TIME: 3:44 PM
 */
@Component
public class ComputeClientHystrix implements ComputeClient {

    @Override
    public int add(int a, int b) {
        return -9999;
    }
}
