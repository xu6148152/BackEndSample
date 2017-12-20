package com.zheng.cms.job.jms;

/**
 * MQ消费者
 * Created by ZhangShuzheng on 2016/11/24.
 */
//public class DefaultMessageQueueListener implements MessageListener {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageQueueListener.class);
//
//    @Autowired
//    ThreadPoolTaskExecutor threadPoolTaskExecutor;
//
//    @Override
//    public void onMessage(final Message message) {
//        // 使用线程池多线程处理
//        threadPoolTaskExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                TextMessage textMessage = (TextMessage) message;
//                try {
//                    String text = textMessage.getText();
//                    LOGGER.info("消费：{}", text);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//}
