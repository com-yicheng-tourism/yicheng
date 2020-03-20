package com.yicheng.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.user.req.UserRegisterReq;
import com.yicheng.tourism.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户信息接口",description = "用户信息接口")
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "查询所有的用户信息")
    @GetMapping("query")
    public String qryAllUser(){
        return null;
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public BaseResponse<String> register( UserRegisterReq userRegisterReq, HttpServletRequest request){
        if (StringUtils.isEmpty(userRegisterReq)){
            return new BaseResponse<>("注册信息不能为空!");
        }
        log.info("请求参数:{}", JSON.toJSONString(userRegisterReq));
        return new BaseResponse<>(userService.register(userRegisterReq,request));
    }
}
