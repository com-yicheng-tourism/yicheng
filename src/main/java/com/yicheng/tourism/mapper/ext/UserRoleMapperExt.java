package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapperExt {

    /**
     * 批量插入
     * @param userRoles
     */
    int insertBatch(@Param("list") List<UserRole> userRoles);
}