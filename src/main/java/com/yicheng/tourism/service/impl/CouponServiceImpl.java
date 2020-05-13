package com.yicheng.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.coupon.req.InsertCouponReq;
import com.yicheng.tourism.dto.coupon.req.QryCouponReq;
import com.yicheng.tourism.entity.Coupon;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.CouponMapper;
import com.yicheng.tourism.service.CouponService;
import com.yicheng.tourism.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CouponMapper couponMapper;

    /**
     * 根据条件查询优惠券
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<PageInfo<List<Coupon>>> qry(QryCouponReq req) {
        return null;
    }

    /**
     * 插入优惠券信息
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> insert(InsertCouponReq req) {
        if (StringUtils.isEmpty(req)){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"请求对象为空");
        }
        log.error("请求参数:{}", JSON.toJSONString(req));
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(req,coupon);
        coupon.setSerialCode(UUIDUtil.get());
        coupon.setCreateTime(new Date());
        coupon.setCreateId("laowang");
        log.error("入库参数:{}",JSON.toJSONString(coupon));
        int i = couponMapper.insertSelective(coupon);
        if (i != 0 ){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"插入成功");
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"插入失败");
    }
}
