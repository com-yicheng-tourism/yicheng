package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.meal.req.MealQueryReq;
import com.yicheng.tourism.entity.Commodity;
import com.yicheng.tourism.entity.MealPrice;
import com.yicheng.tourism.entity.Order;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.ext.MealPriceMapperExt;
import com.yicheng.tourism.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealPriceMapperExt mealPriceMapperExt;

    @Override
    public BaseResponse<PageInfo<MealPrice>> findPage(MealQueryReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        PageHelper.startPage(req.getPage(),req.getRows());
        List<MealPrice> mealList = mealPriceMapperExt.qryByCondition(req);
        if (!CollectionUtils.isEmpty(mealList)){
            PageInfo<MealPrice> pageInfo = new PageInfo<>(mealList);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
    }

    @Override
    public Object insert(MealPrice mealPrice) {
        try {
            mealPriceMapperExt.insert(mealPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Object update(MealPrice mealPrice) {
        try {
            mealPriceMapperExt.update(mealPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Object findById(MealPrice mealPrice) {
        List<MealPrice> mealPrices = new ArrayList<>();
        try {
            mealPrices = mealPriceMapperExt.findById(mealPrice);
        } catch (Exception e) {
            return -1;
        }
        return mealPrices;
    }
}
