package com.binea.upms.admin.util;

import com.binea.common.util.JarUtil;
import com.binea.common.util.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by binea
 * Date: 25/1/2018
 * TIME: 10:31 PM
 */
public class BineaAdminUtil implements InitializingBean, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(BineaAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("===== 开始解压BineaAdmin =====");
        String version = PropertiesFileUtil.getInstance().get("BineaAdmin.version");
        LOGGER.info("BineaAdmin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/BineaAdmin-" + version + ".jar");
        LOGGER.info("BineaAdmin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/resources/bineaAdmin");
        LOGGER.info("BineaAdmin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压BineaAdmin完成 =====");
    }
}
