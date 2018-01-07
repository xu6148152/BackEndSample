package com.binea.interceptor;

import com.binea.domain.City;
import com.binea.model.ValidationModel;
import com.binea.service.CityService;
import com.binea.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 5:39 PM
 */

@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOG = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    @Autowired
    ValidationService mValidationService;

    @Autowired
    CityService mCityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("AccessToken executing...");
        boolean flag = false;
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        if (!StringUtils.isEmpty(token)) {
            ValidationModel validationModel = mValidationService.verifyAccessToken(token);
            if (validationModel != null) {
                City city = mCityService.findCityById(validationModel.getUid());
                if (city != null) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().print("AccessToken ERROR");
        }
        return flag;
    }
}
