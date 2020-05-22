package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.coupon.req.InsertCouponReq;
import com.yicheng.tourism.dto.coupon.req.QryCouponReq;
import com.yicheng.tourism.entity.Coupon;

import java.util.List;

public interface CouponService {

    /**根据条件查询优惠券
     * @param req
     * @return
     */
    BaseResponse<PageInfo<Coupon>> qry(QryCouponReq req);

    /**插入优惠券信息
     * @param req
     * @return
     */
    BaseResponse<String> insert(InsertCouponReq req);

    /**编辑优惠券信息
     * @param req
     * @return
     */
    BaseResponse<String> edit(InsertCouponReq req);
}
