package com.binea.cms.web.controller;

import com.binea.cms.dao.model.User;
import com.binea.common.util.JmsUtil;
import net.sf.json.JSONObject;
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
 * Date: 11/1/2018
 * TIME: 10:42 PM
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
        User user = null;
        for (int i = 1; i <= 1000; i++) {
            user = new User();
//            user.setPasswordUsername("用户" + i);
//            user.setPassword("123456");
//            user.setNickname("昵称");
//            user.setSex(1);
            user.setCtime(System.currentTimeMillis());
//            user.setContent("用户描述");
//			jmsQueueTemplate.convertAndSend(defaultQueueDestination, user);
            JmsUtil.sendMessage(jmsQueueTemplate, defaultQueueDestination, JSONObject.fromObject(user).toString());
        }
        _log.info("发送消息消耗时间" + (System.currentTimeMillis() - start));
        return "success";
    }

    public static void main(String[] args) {
        User user = new User();
//        user.setUsername("用户");
//        user.setPassword("123456");
//        user.setNickname("昵称");
//        user.setSex(1);
        user.setCtime(System.currentTimeMillis());
//        user.setContent("用户描述");
        System.out.println(JSONObject.fromObject(user).toString());
    }
}
