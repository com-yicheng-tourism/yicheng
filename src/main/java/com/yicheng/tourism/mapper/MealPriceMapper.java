package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.MealPrice;
import com.yicheng.tourism.entity.MealPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MealPriceMapper {
    long countByExample(MealPriceExample example);

    int deleteByExample(MealPriceExample example);

    int deleteByPrimaryKey(String id);

    int insert(MealPrice record);

    int insertSelective(MealPrice record);

    List<MealPrice> selectByExample(MealPriceExample example);

    MealPrice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MealPrice record, @Param("example") MealPriceExample example);

    int updateByExample(@Param("record") MealPrice record, @Param("example") MealPriceExample example);

    int updateByPrimaryKeySelective(MealPrice record);

    int updateByPrimaryKey(MealPrice record);
}