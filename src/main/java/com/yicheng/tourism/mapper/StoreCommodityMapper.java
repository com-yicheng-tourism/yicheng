package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.StoreCommodity;
import com.yicheng.tourism.entity.StoreCommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreCommodityMapper {
    long countByExample(StoreCommodityExample example);

    int deleteByExample(StoreCommodityExample example);

    int deleteByPrimaryKey(String id);

    int insert(StoreCommodity record);

    int insertSelective(StoreCommodity record);

    List<StoreCommodity> selectByExample(StoreCommodityExample example);

    StoreCommodity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StoreCommodity record, @Param("example") StoreCommodityExample example);

    int updateByExample(@Param("record") StoreCommodity record, @Param("example") StoreCommodityExample example);

    int updateByPrimaryKeySelective(StoreCommodity record);

    int updateByPrimaryKey(StoreCommodity record);
}