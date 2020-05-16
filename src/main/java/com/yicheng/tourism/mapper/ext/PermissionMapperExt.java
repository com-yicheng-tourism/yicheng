package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapperExt {

    /**根据条件进行查询权限列表
     * @param req
     * @return
     */
    List<Permission> qryByCondition(@Param("c") PermissionConditionReq req);

    List<Permission> qryByRole(@Param("c") List<String> roleId);

    @Select("select * from t_permission")
    List<Permission> qryAll();
}