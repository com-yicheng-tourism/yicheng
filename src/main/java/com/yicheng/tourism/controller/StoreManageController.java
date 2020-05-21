package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.Store;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.service.StoreManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ApiOperation(value = "插入商铺数据")
    @RequestMapping(value = "/insertStore",method = RequestMethod.POST)
    public Object insertStore(@RequestBody StoreUpdateReq store){
        return storeManageService.insertStore(store);
    }

    @ApiOperation(value = "更新商铺数据")
    @RequestMapping(value = "/updateStore",method = RequestMethod.POST)
    public Object updateStore(@RequestBody StoreUpdateReq store){
        return storeManageService.updateStore(store);
    }

    @ApiOperation(value = "强制关闭店铺")
    @RequestMapping(value = "/close",method = RequestMethod.GET)
    public BaseResponse<String> closeStore(String id, HttpServletRequest request){
        return storeManageService.closeStore(id ,request);
    }

    @ApiOperation(value = "删除商铺数据")
    @RequestMapping(value = "/deleteStore",method = RequestMethod.POST)
    public Object deleteStore(@RequestBody String id){
        return storeManageService.deleteStore(id);
    }
}
