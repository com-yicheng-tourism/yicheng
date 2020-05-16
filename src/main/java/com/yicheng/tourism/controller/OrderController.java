package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.order.req.OrderQueryReq;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.entity.Order;
import com.yicheng.tourism.entity.Store;
import com.yicheng.tourism.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "订单管理接口",description = "订单管理接口")
@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "订单按条件分页查询")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResponse<PageInfo<Order>> findPageDate(OrderQueryReq req){
        return orderService.findPage(req);
    }
}
