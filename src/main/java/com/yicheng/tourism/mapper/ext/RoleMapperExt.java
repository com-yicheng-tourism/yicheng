package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.resp.RoleResp;
import com.yicheng.tourism.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapperExt {

    /**根据条件查询角色
     * @param req
     * @return
     */
    List<Role> qryByCondition(@Param("c")RoleConditionReq req);

    /**根据用户名获取角色
     * @param username
     * @return
     */
    List<Role> qryByUsername(String username);
}