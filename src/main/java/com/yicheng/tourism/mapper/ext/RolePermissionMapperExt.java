package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapperExt {

    /**
     * 批量插入
     * @param rolePermissions
     */
    int insertBatch(@Param("list")List<RolePermission> rolePermissions);
}