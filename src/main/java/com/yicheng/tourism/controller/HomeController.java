package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.home.resp.HomeCountResp;
import com.yicheng.tourism.dto.role.req.AssignRoleReq;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.dto.user.resp.UserQryResp;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.UserMapper;
import com.yicheng.tourism.mapper.ext.UserMapperExt;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.util.IpUtil;
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

@Api(value = "首页信息接口",description = "首页信息接口")
@RestController
@RequestMapping("home")
@Slf4j
public class HomeController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UserMapperExt userMapperExt;
    @ApiOperation(value = "首页数据统计")
    @GetMapping("count")
    public BaseResponse<HomeCountResp> count(){
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),userMapperExt.homeCount());
    }


}
