package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.model.PageParam;
import com.yicheng.tourism.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public BaseResponse<String> register(@RequestBody UserRegisterOrLoginReq userRegisterOrLoginReq, HttpServletRequest request){
        return userService.register(userRegisterOrLoginReq,request);
    }
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponse<String> login(@RequestBody  UserRegisterOrLoginReq userRegisterOrLoginReq, HttpServletRequest request){
       return userService.login(userRegisterOrLoginReq,request);
    }
    @ApiOperation(value = "用户详情")
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public BaseResponse<User> detail( String serialCode){
        return userService.getDetail(serialCode);
    }
    @ApiOperation(value = "用户编辑/修改")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public BaseResponse<String> edit(@RequestBody UpdateUserInfoReq req){
        return userService.edit(req);
    }
    @ApiOperation(value = "用户列表查询")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<PageInfo<User>> qryByPage(UserQryReq req, PageParam param){
        return new BaseResponse<>(userService.qryByCondition(req,param));
    }
}
