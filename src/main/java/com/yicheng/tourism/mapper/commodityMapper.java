package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.commodity;
import com.yicheng.tourism.entity.commodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface commodityMapper {
    long countByExample(commodityExample example);

    int deleteByExample(commodityExample example);

    int deleteByPrimaryKey(String id);

    int insert(commodity record);

    int insertSelective(commodity record);

    List<commodity> selectByExample(commodityExample example);

    commodity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") commodity record, @Param("example") commodityExample example);

    int updateByExample(@Param("record") commodity record, @Param("example") commodityExample example);

    int updateByPrimaryKeySelective(commodity record);

    int updateByPrimaryKey(commodity record);
}