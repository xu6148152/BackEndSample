package com.binea.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by binea
 * Date: 28/2/2018
 * TIME: 10:40 PM
 */
public class BineaAdminUtil implements InitializingBean, ServletContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(BineaAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("===== 开始解压zheng-admin =====");
        String version = PropertiesFileUtil.getInstance("binea-admin-client").get("binea.admin.version");
        LOGGER.info("binea-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/binea-admin-" + version + ".jar");
        LOGGER.info("binea-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/binea-admin";
        LOGGER.info("binea-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压zheng-admin完成 =====");
    }
}
