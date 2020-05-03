package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.entity.Store;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.service.StoreManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "店铺管理接口",description = "店铺管理接口")
@RequestMapping("store")
@RestController
@Slf4j
public class StoreManageController {

    @Autowired
    private StoreManageService storeManageService;

    @ApiOperation(value = "商铺按条件分页查询")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResponse<PageInfo<Store>> findPageDate(StoreQueryReq req){
        return storeManageService.findPage(req);
    }

}
