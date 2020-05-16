package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.meal.req.MealQueryReq;
import com.yicheng.tourism.dto.order.req.OrderQueryReq;
import com.yicheng.tourism.entity.Commodity;
import com.yicheng.tourism.entity.MealPrice;
import com.yicheng.tourism.mapper.ext.MealPriceMapperExt;
import com.yicheng.tourism.service.MealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "套餐管理接口",description = "套餐管理接口")
@RequestMapping("meal")
@RestController
@Slf4j
public class MealController {

    @Autowired
    private MealService mealService;

    @ApiOperation(value = "套餐按条件分页查询")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResponse<PageInfo<MealPrice>> findPageDate(MealQueryReq req){
        return mealService.findPage(req);
    }

    @ApiOperation(value = "插入套餐数据")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object insert(@RequestBody MealPrice mealPrice){
        return mealService.insert(mealPrice);
    }

    @ApiOperation(value = "更新套餐数据")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object update(@RequestBody MealPrice mealPrice){
        return mealService.update(mealPrice);
    }

    @ApiOperation(value = "根据id查询数据套餐数据")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Object queryById(@RequestBody MealPrice mealPrice){
        return mealService.findById(mealPrice);
    }
}
