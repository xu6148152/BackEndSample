package com.binea.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by binea
 * Date: 7/3/2018
 * TIME: 10:07 PM
 */
public class BineaCmsRpcServiceApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BineaCmsRpcServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info(">>>>> binea-cms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        LOGGER.info(">>>>> binea-cms-rpc-service 启动完成 <<<<<");
    }
}
