//package com.yicheng.tourism.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.yicheng.tourism.entity.User;
//import com.yicheng.tourism.service.LoginService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//@RestController
//public class LoginController {
//    @Autowired
//    private LoginService loginService;
//
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public void login(String username,String password) {
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(new UsernamePasswordToken(username, password));
//            System.out.println("登录成功!");
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            System.out.println("登录失败!");
//        }
//    }
//
////    @RequestMapping("/afterlogin")
////    public void recTicket(String ticket, HttpServletRequest request, HttpServletResponse response) {
////        Map<String, String> paramMap = new HashMap<>(1);
////        paramMap.put("ticket", ticket);
////        String result = HttpHandler.getInstance().usingGetMethod(OPSConstants.OPS_URL_WITH_TICKET, paramMap, null);
////        User user = JSON.parseObject(result, User.class);
////        loginService.registerOrLogin(user);
////        HttpSession session = request.getSession();
////        session.setMaxInactiveInterval(1000 * 60 * 60);
////        session.setAttribute(session.getId(), user);
////        try {
////            response.sendRedirect("/html/index.html");
////        } catch (IOException e) {
////
////        }
////    }
////
////    @RequestMapping(value = "/logout")
////    public void logout(HttpServletRequest request, HttpServletResponse response) {
////        HttpSession session = request.getSession();
////        User user = (User) session.getAttribute(session.getId());
////        String pticket = user.getPticket();
////        String url = OPSConstants.OPS_URL_LOGOUT + "&pticket=" + pticket;
////        response.setStatus(302);
////        try {
////            response.sendRedirect(url);
////        } catch (IOException e) {
////
////        }
////    }
////
////    @RequestMapping(value = "/afterlogout")
////    public void afterLogout(HttpServletResponse response) {
////        //这里一定要使用shiro退出方式，否则session失效
////        SecurityUtils.getSubject().logout();
////        try {
////            response.sendRedirect(OPSConstants.OPS_URL_WITH_RETURN);
////        } catch (IOException e) {
////
////        }
////    }
//}
