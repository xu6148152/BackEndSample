package com.binea.upms.admin.controller;

import com.binea.common.util.RedisUtil;
import com.binea.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by binea
 * Date: 18/1/2018
 * TIME: 10:46 PM
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    private static Logger _log = LoggerFactory.getLogger(SSOController.class);

    private static List<String> apps = new ArrayList<>();

    static {
        apps.add("binea-cms-job");
        apps.add("binea-cms-web");
        apps.add("binea-cms-admin");
        apps.add("binea-upms-server");
    }

    @Autowired
    UpmsSystemService upmsSystemService;

    /**
     * 认证中心首页
     *
     * @return
     */
    @RequestMapping("")
    public String index(HttpServletRequest request) throws Exception {
        HttpSession httpSession = request.getSession();

        String system_name = request.getParameter("system_name");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isEmpty(system_name) || !apps.contains(system_name)) {
            _log.info("未注册的系统：{}", system_name);
            return "/404";
        }
        // 判断是否存在全局会话
        if (StringUtils.isEmpty(RedisUtil.get(httpSession.getId() + "_token"))) {
            return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
        }

        // 已登录
        String token = RedisUtil.get(httpSession.getId()) + "_token";
        String redirectUrl = backurl;
        if (backurl.contains("?")) {
            redirectUrl += "&token=" + token;
        } else {
            redirectUrl += "?token=" + token;
        }
        _log.info("认证中心验证为已登录，跳回：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    /**
     * 登录页get
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/sso/login";
    }

    /**
     * 登录页post
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String backurl = request.getParameter("backurl");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username)) {
            _log.info("帐号不能为空！");
            return "/404";
        }
        if (StringUtils.isEmpty(password)) {
            _log.info("密码不能为空！");
            return "/404";
        }
        // 默认验证帐号密码正确，创建token
        String token = UUID.randomUUID().toString();
        RedisUtil.set(session.getId() + "_token", token, 2 * 60 * 60);
        RedisUtil.set(token, token, 2 * 60 * 60);
        String redirectUrl = backurl;
        if (backurl.contains("?")) {
            redirectUrl += "&token=" + token;
        } else {
            redirectUrl += "?token=" + token;
        }
        _log.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/token")
    @ResponseBody
    public String token(HttpServletRequest request) {
        String tokenParam = request.getParameter("token");
        String token = RedisUtil.get(tokenParam);
        if (StringUtils.isEmpty(tokenParam) || !tokenParam.equals(token)) {
            return "failed";
        }
        return "success";
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 清除全局会话
        String token = RedisUtil.get(session.getId() + "_token");
        RedisUtil.remove(session.getId() + "_token");
        RedisUtil.remove(token);
        // 通知该token的子系统退出登录
        // TODO
        return "/sso/login";
    }
}
