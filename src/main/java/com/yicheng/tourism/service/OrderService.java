package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.order.req.OrderQueryReq;
import com.yicheng.tourism.entity.Order;

public interface OrderService {

    BaseResponse<PageInfo<Order>> findPage(OrderQueryReq req);
}
