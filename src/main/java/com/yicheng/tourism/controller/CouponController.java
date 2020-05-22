package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.coupon.req.InsertCouponReq;
import com.yicheng.tourism.dto.coupon.req.QryCouponReq;
import com.yicheng.tourism.dto.role.req.AssignRoleReq;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.dto.user.resp.UserQryResp;
import com.yicheng.tourism.entity.Coupon;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.service.CouponService;
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

@Api(value = "优惠券管理",description = "优惠券管理")
@RestController
@RequestMapping("coupon")
@Slf4j
public class CouponController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private CouponService couponService;

    @ApiOperation(value = "优惠券信息按条件分页查询")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<PageInfo<Coupon>> qryByPage(QryCouponReq req){
        return couponService.qry(req);
    }

    @ApiOperation(value = "插入优惠券信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse<String> insert(@RequestBody InsertCouponReq req){
        return couponService.insert(req);
    }

    @ApiOperation(value = "编辑优惠券信息")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public BaseResponse<String> edit(@RequestBody InsertCouponReq req){
        return couponService.edit(req);
    }

}
