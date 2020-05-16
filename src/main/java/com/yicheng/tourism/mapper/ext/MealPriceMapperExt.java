package com.yicheng.tourism.mapper.ext;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.meal.req.MealQueryReq;
import com.yicheng.tourism.entity.MealPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MealPriceMapperExt {

    void insertBatch(List<MealPrice> list);

    List<MealPrice> qryByCondition(@Param("c") MealQueryReq req);

    void insert(@Param("c")MealPrice mealPrice);

    void update(@Param("c")MealPrice mealPrice);

    List<MealPrice> findById(@Param("c")MealPrice mealPrice);
}
