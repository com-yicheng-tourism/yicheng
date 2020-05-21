package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.Commodity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommodityService {
    BaseResponse<PageInfo<Commodity>> findPage(CommodityQueryReq req);

    Object insertCommodity(CommodityQueryReq req);

    Object deleteCommodity(Commodity com);

    Object updateCommodity(Commodity com);

    Object findById(Commodity com);

    List<Commodity> getShoppingCart(String userId);

    BaseResponse<String> addToShoppingCart(CommodityQueryReq req, HttpServletRequest request);
}
