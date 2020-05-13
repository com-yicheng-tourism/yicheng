package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.commodity;

public interface CommodityService {
    BaseResponse<PageInfo<commodity>> findPage(CommodityQueryReq req);

    Object insertCommodity(commodity com);

    Object deleteCommodity(commodity com);

    Object updateCommodity(commodity com);

    Object findById(commodity com);
}
