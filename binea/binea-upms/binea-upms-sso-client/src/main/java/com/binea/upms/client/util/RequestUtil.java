package com.binea.upms.client.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by binea
 * Date: 22/1/2018
 * TIME: 9:56 PM
 */
public class RequestUtil {
    /**
     * 移除request指定参数
     *
     * @param request
     * @param paramName
     * @return
     */
    public String removeParam(HttpServletRequest request, String paramName) {
        String queryString = "";
        Enumeration keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (key.equals(paramName)) {
                continue;
            }
            if (queryString.equals("")) {
                queryString = key + "=" + request.getParameter(key);
            } else {
                queryString += "&" + key + "=" + request.getParameter(key);
            }
        }
        return queryString;
    }
}
