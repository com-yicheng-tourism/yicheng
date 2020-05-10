package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.NavPermission;
import com.yicheng.tourism.entity.NavPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NavPermissionMapper {
    long countByExample(NavPermissionExample example);

    int deleteByExample(NavPermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(NavPermission record);

    int insertSelective(NavPermission record);

    List<NavPermission> selectByExample(NavPermissionExample example);

    NavPermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NavPermission record, @Param("example") NavPermissionExample example);

    int updateByExample(@Param("record") NavPermission record, @Param("example") NavPermissionExample example);

    int updateByPrimaryKeySelective(NavPermission record);

    int updateByPrimaryKey(NavPermission record);
}