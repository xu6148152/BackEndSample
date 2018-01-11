package com.binea.cms.web.jms;

import com.binea.cms.dao.model.User;
import com.binea.cms.service.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by binea
 * Date: 11/1/2018
 * TIME: 10:26 PM
 */
public class DefaultMessageQueueListener implements MessageListener {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    UserService userService;

    @Override
    public void onMessage(Message message) {
        threadPoolTaskExecutor.execute(() -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                JSONObject json = JSONObject.fromObject(text);
                User user = (User) JSONObject.toBean(json, User.class);
                userService.getMapper().insertSelective(user);
                LOGGER.info("binea-cmsg-mqj接收到: {}", text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
