package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.Nav;
import com.yicheng.tourism.entity.NavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NavMapper {
    long countByExample(NavExample example);

    int deleteByExample(NavExample example);

    int deleteByPrimaryKey(String id);

    int insert(Nav record);

    int insertSelective(Nav record);

    List<Nav> selectByExample(NavExample example);

    Nav selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Nav record, @Param("example") NavExample example);

    int updateByExample(@Param("record") Nav record, @Param("example") NavExample example);

    int updateByPrimaryKeySelective(Nav record);

    int updateByPrimaryKey(Nav record);
}