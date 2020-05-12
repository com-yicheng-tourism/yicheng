package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.Store;
import com.yicheng.tourism.entity.commodity;
import com.yicheng.tourism.service.CommodityService;
import com.yicheng.tourism.service.StoreManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "商品管理接口",description = "商品管理接口")
@RequestMapping("commodity")
@RestController
@Slf4j
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @ApiOperation(value = "商品按条件分页查询")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResponse<PageInfo<commodity>> findPageDate(CommodityQueryReq req){
        return commodityService.findPage(req);
    }

    @ApiOperation(value = "插入商铺数据")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object insert(@RequestBody commodity com){
        return commodityService.insertCommodity(com);
    }

    @ApiOperation(value = "更新商品数据")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object update(@RequestBody commodity com){
        return commodityService.updateCommodity(com);
    }

    @ApiOperation(value = "删除商品数据")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(String id){
        return commodityService.deleteCommodity(id);
    }
}
