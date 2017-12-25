package com.binea.controller;

import com.binea.common.util.JmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;

/**
 * Created by binea
 * Date: 25/12/2017
 * TIME: 10:12 PM
 */
@Controller
@RequestMapping("/activemq")
public class ActiveMQController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(ActiveMQController.class);

    @Autowired
    JmsTemplate jmsQueueTemplate;

    @Autowired
    Destination defaultQueueDestination;

    @RequestMapping("/send")
    @ResponseBody
    public Object send() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            _log.info("发送消息" + (i + 1));
            JmsUtil.sendMessage(jmsQueueTemplate, defaultQueueDestination, "消息" + (i + 1));
        }
        _log.info("发送消息消耗时间" + (System.currentTimeMillis() - start));
        return "success";
    }
}