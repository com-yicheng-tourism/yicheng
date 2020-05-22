package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.Commodity;
import com.yicheng.tourism.service.CommodityService;
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

@Api(value = "商品管理接口",description = "商品管理接口")
@RequestMapping("commodity")
@RestController
@Slf4j
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @ApiOperation(value = "商品按条件分页查询")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResponse<PageInfo<Commodity>> findPageDate(CommodityQueryReq req){
        return commodityService.findPage(req);
    }

    @ApiOperation(value = "插入商铺数据")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object insert(@RequestBody CommodityQueryReq req){
        return commodityService.insertCommodity(req);
    }

    @ApiOperation(value = "更新商品数据")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object update(@RequestBody Commodity com){
        return commodityService.updateCommodity(com);
    }

    @ApiOperation(value = "删除商品数据")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(@RequestBody Commodity com){
        return commodityService.deleteCommodity(com);
    }

    @ApiOperation(value = "根据id查询数据商品数据")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Object queryById(@RequestBody Commodity com){
        return commodityService.findById(com);
    }

    @ApiOperation(value = "查询购物车")
    @RequestMapping(value = "/shoppingCart",method = RequestMethod.GET)
    public List<Commodity> shoppingCart(String userId){
        return commodityService.getShoppingCart(userId);
    }

    @ApiOperation(value = "查询购物车")
    @RequestMapping(value = "/addToShoppingCart",method = RequestMethod.POST)
    public BaseResponse<String> addToShoppingCart(@RequestBody  CommodityQueryReq req, HttpServletRequest request){
        return commodityService.addToShoppingCart(req,request);
    }

    @ApiOperation(value = "上下架商品")
    @RequestMapping(value = "/close",method = RequestMethod.POST)
    public BaseResponse<String> changeState(String id, HttpServletRequest request){
        return commodityService.changeState(id ,request);
    }
}
