package com.binea.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by binea
 * Date: 12/11/2017
 * TIME: 11:10 PM
 */
public class AccessFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class.getCanonicalName());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        LOGGER.info("{} request to {}", request.getMethod(), request.getRequestURL());

        Object accessToken = request.getParameter("accessToken");

        if(accessToken == null) {
            LOGGER.warn("access token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        LOGGER.info("access token ok");
        return null;
    }
}
