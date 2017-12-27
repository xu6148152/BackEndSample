package com.binea.web;

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
 * Date: 25/12/2017
 * TIME: 10:17 PM
 */

public class DefaultMessageQueueListener implements MessageListener {

    private static Logger _log = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    UserService userService;

    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            public void run() {
                TextMessage textMessage = (TextMessage) message;
                try {
//                    _log.info("defaultQueueMessageListener接收到：{}", textMessage.getText());
                    String text = textMessage.getText();
                    JSONObject json = JSONObject.fromObject(text);
                    User user = (User) JSONObject.toBean(json, User.class);
                    userService.getMapper().insertSelective(user);
                    _log.info("cms-web接收到：{}", text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
