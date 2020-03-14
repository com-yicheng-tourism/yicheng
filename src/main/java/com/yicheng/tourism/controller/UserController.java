package com.yicheng.tourism.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "用户信息接口",description = "用户信息接口")
@RestController
@RequestMapping("user")
public class UserController {
    @ApiOperation(value = "查询所有的用户信息")
    @GetMapping("query")
    public String qryAllUser(){
        return null;
    }
}
