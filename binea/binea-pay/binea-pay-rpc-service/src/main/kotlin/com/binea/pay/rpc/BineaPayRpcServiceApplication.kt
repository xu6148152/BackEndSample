package com.binea.pay.rpc

import org.slf4j.LoggerFactory
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:31 PM
 */
class BineaPayRpcServiceApplication {
    private val LOGGER = LoggerFactory.getLogger(BineaPayRpcServiceApplication::class.java)

    fun main(args: Array<String>) {
        LOGGER.info(">>>>> binea-pay-rpc-service 正在启动 <<<<<")
        ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml")
        LOGGER.info(">>>>> binea-pay-rpc-service 启动完成 <<<<<")
    }
}