package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.entity.Store;

public interface StoreManageService {
    /**
     * 获取数据列表
     * @param req
     * @return
     */
    BaseResponse<PageInfo<Store>> findPage(StoreQueryReq req);
}
