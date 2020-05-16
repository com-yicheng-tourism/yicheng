package com.yicheng.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.coupon.req.InsertCouponReq;
import com.yicheng.tourism.dto.coupon.req.QryCouponReq;
import com.yicheng.tourism.entity.Coupon;
import com.yicheng.tourism.entity.MealPrice;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.CouponMapper;
import com.yicheng.tourism.mapper.ext.CouponMapperExt;
import com.yicheng.tourism.service.CouponService;
import com.yicheng.tourism.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CouponMapperExt couponMapperExt;

    @Autowired
    private CouponMapper couponMapper;

    /**
     * 根据条件查询优惠券
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<PageInfo<Coupon>> qry(QryCouponReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        PageHelper.startPage(req.getPage(),req.getRows());
        List<Coupon> couponList = couponMapperExt.qryByCondition(req);
        if (!CollectionUtils.isEmpty(couponList)){
            PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
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
