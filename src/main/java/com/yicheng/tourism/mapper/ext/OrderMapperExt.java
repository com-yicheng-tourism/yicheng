package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.order.req.OrderQueryReq;
import com.yicheng.tourism.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapperExt {

    List<Order> qryByCondition(@Param("c")OrderQueryReq req);
}
