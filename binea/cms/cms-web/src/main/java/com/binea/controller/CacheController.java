package com.binea.controller;

import com.binea.common.util.EhCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by binea
 * Date: 6/12/2017
 * TIME: 9:48 PM
 */
@RequestMapping("/cache")
@RestController
public class CacheController {
    private final static Logger logger = LoggerFactory.getLogger(CacheController.class);

    private final static String CACHE_NAME = "ehcache_common";

    private final static String SUCCESS = "success";

    @RequestMapping("/add")
    @ResponseBody
    public Object add(HttpServletRequest request) {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        EhCacheUtil.put(CACHE_NAME, key, value);
        return SUCCESS;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(HttpServletRequest request) {
        String key = request.getParameter("key");
        EhCacheUtil.remove(CACHE_NAME, key);
        return SUCCESS;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(HttpServletRequest request) {
        String key = request.getParameter("key");
        Object object = EhCacheUtil.get(CACHE_NAME, key);
        if (null == object) {
            logger.debug("can't find key={} record", key);
            return "value";
        }
        return object;
    }
}
