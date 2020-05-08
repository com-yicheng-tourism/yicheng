package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.role.req.AssignRoleReq;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.dto.user.resp.UserQryResp;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.util.IpUtil;
import com.yicheng.tourism.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "用户信息接口",description = "用户信息接口")
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
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
    public BaseResponse<UserQryResp> login(@RequestBody  UserRegisterOrLoginReq userRegisterOrLoginReq, HttpServletRequest request){
       return userService.login(userRegisterOrLoginReq,request);
    }

    @ApiOperation(value = "获取token信息获取用户")
    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public BaseResponse<Object> getToken(HttpServletRequest request){
        User user = userService.getToken(request);
        if (StringUtils.isEmpty(user)){
            return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),RespStatusEnum.TOKEN_FAILURE.getMessage());
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),user.getUserName());
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

    @ApiOperation(value = "用户按条件分页查询")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<PageInfo<User>> qryByPage(UserQryConditionReq req){
        return userService.qryByCondition(req);
    }

    @ApiOperation(value = "获取用户IP")
    @RequestMapping(value = "/getIp",method = RequestMethod.GET)
    public BaseResponse<String> getIp(HttpServletRequest request){
        String ipAddr = IpUtil.getIpAddr(request);
//        String ipAddr = IpUtil.getIpAddress(request);
//        String ipInfo = IpUtil.getIpInfo("192.168.31.10");
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),ipAddr);
    }
    @ApiOperation(value = "用户分配权限")
    @RequestMapping(value = "/assignRole",method = RequestMethod.POST)
    public BaseResponse<String> assignRole(@RequestBody List<AssignRoleReq> req,HttpServletRequest request) {
        return userService.assignRole(req,request);
    }

    @ApiOperation(value = "权限验证")
    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    public BaseResponse<String> verification(String username ,String apiUrl ,  HttpServletRequest request) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        User userId = (User) request.getSession().getAttribute("userId");
        User user = (User) valueOperations.get(userId.getUserName());
        if (StringUtils.isEmpty(user)){
            return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),RespStatusEnum.TOKEN_FAILURE.getMessage());
        }
        return userService.verification(username,apiUrl);
    }
}
