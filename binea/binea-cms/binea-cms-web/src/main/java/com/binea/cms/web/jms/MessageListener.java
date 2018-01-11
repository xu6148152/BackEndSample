package com.binea.cms.web.jms;

import com.binea.cms.dao.model.User;
import com.binea.cms.service.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;

/**
 * Created by binea
 * Date: 11/1/2018
 * TIME: 10:46 PM
 */
@Component
public class MessageListener extends MessageListenerAdapter {
    private static Logger _log = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    UserService userService;

    @JmsListener(containerFactory = "connectionFactory", destination = "defaultQueueDestination")
    public void processOrder(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            public void run() {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    JSONObject json = JSONObject.fromObject(text);
                    User user = (User) JSONObject.toBean(json, User.class);
                    userService.getMapper().insertSelective(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
