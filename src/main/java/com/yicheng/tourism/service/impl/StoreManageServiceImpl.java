package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.entity.Store;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.ext.StoreMapperExt;
import com.yicheng.tourism.service.StoreManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StoreManageServiceImpl implements StoreManageService {

    @Autowired
    private StoreMapperExt storeMapperExt;

    @Override
    public BaseResponse<PageInfo<Store>> findPage(StoreQueryReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        PageHelper.startPage(req.getPage(),req.getRows());
        List<Store> storeList = storeMapperExt.qryByCondition(req);
        if (!CollectionUtils.isEmpty(storeList)){
            PageInfo<Store> pageInfo = new PageInfo<>(storeList);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
    }
}
