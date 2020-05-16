package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.meal.req.MealQueryReq;
import com.yicheng.tourism.entity.MealPrice;

public interface MealService {

    BaseResponse<PageInfo<MealPrice>> findPage(MealQueryReq req);

    Object insert(MealPrice mealPrice);

    Object update(MealPrice mealPrice);

    Object findById(MealPrice mealPrice);
}
