package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.coupon.req.QryCouponReq;
import com.yicheng.tourism.entity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapperExt {

    /**根据条件查询优惠券信息
     * @param req
     * @return
     */
   List<Coupon> qry(@Param("c") QryCouponReq req);

    List<Coupon> qryByCondition(@Param("c") QryCouponReq req);
}
