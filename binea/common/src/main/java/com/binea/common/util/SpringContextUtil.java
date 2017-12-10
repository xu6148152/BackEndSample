package com.binea.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by binea
 * Date: 10/12/2017
 * TIME: 4:23 PM
 */
public class SpringContextUtil implements ApplicationContextAware {

    public static SpringContextUtil springContextUtil = new SpringContextUtil();

    private static ApplicationContext context = null;

    public final static synchronized SpringContextUtil getInstance() {
        return springContextUtil;
    }

    private SpringContextUtil() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

}
