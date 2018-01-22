package com.binea.upms.client.filter;

import com.binea.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binea
 * Date: 16/1/2018
 * TIME: 10:53 PM
 */
public class SSOFilter implements Filter {

    private static Logger _log = LoggerFactory.getLogger(SSOFilter.class);

    private String SYSTEM_NAME = "system_name";
    private String SSO_SERVER_URL = "sso_server_url";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // 已登录
        if (!StringUtils.isEmpty(RedisUtil.get(session.getId() + "_token"))) {
            filterChain.doFilter(request, response);
            return;
        }
        // 未登录
        else {
            //认证中心地址
            StringBuilder sb = new StringBuilder(filterConfig.getInitParameter(SSO_SERVER_URL));
            sb.append("/sso");
            String token = request.getParameter("token");
            // 无token，跳到认证中心登录
            if (!StringUtils.isEmpty(token)) {
                try {
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost httpPost = new HttpPost(sb.toString() + "/token");
                    List<NameValuePair> nvps = new ArrayList();
                    nvps.add(new BasicNameValuePair("token", token));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String result = EntityUtils.toString(httpEntity);
                        if (result.equals("success")) {
                            RedisUtil.set(session.getId() + "_token", token);
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                } catch (IOException e) {
                    _log.error("验证token失败: ", e);
                }
            }

            sb.append("?").append(SYSTEM_NAME).append("=").append(filterConfig.getInitParameter(SYSTEM_NAME));
            StringBuffer backUrl = request.getRequestURL();
            String queryString = request.getQueryString();
            if (!StringUtils.isEmpty(queryString)) {
                backUrl.append("?").append(queryString);
            }

            sb.append("&").append("backurl").append("=").append(URLEncoder.encode(backUrl.toString(), "utf-8"));

            _log.info("未登录，跳转认证中心: {}", sb);
            response.sendRedirect(sb.toString());
        }
    }

    @Override
    public void destroy() {

    }

}
