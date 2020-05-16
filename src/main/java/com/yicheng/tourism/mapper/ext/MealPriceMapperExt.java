package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.entity.MealPrice;

import java.util.List;

public interface MealPriceMapperExt {

    void insertBatch(List<MealPrice> list);
}
