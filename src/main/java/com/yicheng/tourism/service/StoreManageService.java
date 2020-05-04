package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.Store;

import java.util.List;

public interface StoreManageService {
    /**
     * 获取数据列表
     * @param req
     * @return
     */
    BaseResponse<PageInfo<Store>> findPage(StoreQueryReq req);

    /**
     * 插入商铺信息
     * @param store
     * @return
     */
    int insertStore(StoreUpdateReq store);

    /**
     * 更新商铺信息
     * @param store
     * @return
     */
    int updateStore(StoreUpdateReq store);

    /**
     * 删除商铺信息
     * @param id
     * @return
     */
    int deleteStore(String id);
}
