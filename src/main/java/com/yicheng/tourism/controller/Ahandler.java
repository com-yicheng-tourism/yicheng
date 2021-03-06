package com.yicheng.tourism.controller;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Ahandler {

    // 登录的url
    @RequestMapping({"/login", "/"})
    public String indexHtml() {
        return "/page-login";
    }

    @RequestMapping(value="/{page}",method= RequestMethod.GET)
    public String page(@PathVariable("page") String page) {
        System.out.println("开始执行"+page+"的跳转");
        return page;
    }

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
