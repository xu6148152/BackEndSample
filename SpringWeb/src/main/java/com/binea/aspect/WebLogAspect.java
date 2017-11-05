package com.binea.aspect;

import com.mongodb.BasicDBObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by binea
 * Date: 5/11/2017
 * TIME: 4:38 PM
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.binea.web..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LOGGER.info("URL: {}", request.getRequestURL().toString());
        LOGGER.info("HTTP_METHOD: {}", request.getMethod());
        LOGGER.info("IP: {}", request.getRemoteAddr());
        LOGGER.info("CLASS_METHOD: {} , {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName()
        );
        LOGGER.info("ARGS: {}", Arrays.toString(joinPoint.getArgs()));

        LOGGER.info("dbObject info {}", getBasicDBObject(request, joinPoint));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Exception {
        LOGGER.info("RESPONSE: {}", ret);
        LOGGER.info("SPEND TIME: {}", (System.currentTimeMillis() - startTime.get()));
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("requestURL", request.getRequestURL().toString());
        dbObject.append("requestURI", request.getRequestURI());
        dbObject.append("queryString", request.getQueryString());
        dbObject.append("remoteAddr", request.getRemoteAddr());
        dbObject.append("remoteHost", request.getRemoteHost());
        dbObject.append("remotePort", request.getRemotePort());
        dbObject.append("localAddr", request.getLocalAddr());
        dbObject.append("localName", request.getLocalName());
        dbObject.append("method", request.getMethod());
        dbObject.append("headers", getHeadersInfo(request));
        dbObject.append("parameters", request.getParameterMap());
        dbObject.append("classMethod",
                        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
        );
        dbObject.append("args", Arrays.toString(joinPoint.getArgs()));
        return dbObject;
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
