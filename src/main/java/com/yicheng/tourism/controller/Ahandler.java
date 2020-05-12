package com.yicheng.tourism.controller;

import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Ahandler {

    @Autowired
    private UserService userService;
    // 登录的url
    @RequestMapping({"/login", "/"})
    public String loginHtml() {
        return "/pages/login";
    }
    @RequestMapping({"/index"})
    public String indexHtml() {
        return "/yicheng";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerHtml() {
        return "/pages/page-register";
    }
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String dataTable() {
        return "/pages/profile";
    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home() {
        return "/home";
    }
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main() {
        return "/main";
    }
    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public String userManage() {
        return "/pages/auth";
    }
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String User(HttpServletRequest request) {
        User user = userService.getToken(request);
        if (StringUtils.isEmpty(user)){
            return "/pages/404";
        }
        return "/pages/user";
    }
    @RequestMapping(value = "/datetest",method = RequestMethod.GET)
    public String testUserElement() {
        return "/testDate";
    }
    @RequestMapping(value = "/store",method = RequestMethod.GET)
    public String storeElement() {
        return "/pages/store";
    }
    @RequestMapping(value = "/commodity",method = RequestMethod.GET)
    public String commodityElement() {
        return "/pages/commodity";
    }
    @RequestMapping(value = "/picture",method = RequestMethod.GET)
    public String picture() {
        return "/pages/picture";
    }
//    @RequestMapping(value="/{page}",method= RequestMethod.GET)
//    public String page(@PathVariable("page") String page) {
//        System.out.println("开始执行"+page+"的跳转");
//        return page;
//    }

    // 属于user角色@RequiresRoles("user")
    // 必须同时属于user和admin角@RequiresRoles({ "user", "admin" })
    // 属于user或者admin之一;修改logical为OR 即可@RequiresRoles(value = { "user", "admin"},
    // logical = Logical.OR)


    @RequestMapping("/showUserHtml.action")
    @RequiresRoles(value = {"user", "admin"}, logical = Logical.OR)
    @RequiresPermissions("user:query")
    public String userHtml() {
        return "/user";
    }


    @RequestMapping("/showAdminHtml.action")
    @RequiresRoles("admin")
    @RequiresPermissions("admin:query")
    public String adminHtml() {

        return "/admin";
    }

    @RequestMapping("/unauthorized.action")
    public String unauthorized() {
        return "/abc";
    }

    @RequestMapping("/LoginSuccess.action")
    public String listHtml() {
        return "/list";
    }


    @RequestMapping("/error.action")
    public String error() {
        int a = 1 / 0;
        return "/abc";
    }

}
