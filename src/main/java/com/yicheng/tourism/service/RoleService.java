package com.yicheng.tourism.service;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.req.RoleInsertReq;
import com.yicheng.tourism.entity.Role;

import java.util.List;

public interface RoleService {

    /**角色添加
     * @param req
     * @return
     */
    BaseResponse<String> insert(RoleInsertReq req);

    /**根据条件进行角色查询
     * @param req
     * @return
     */
    BaseResponse<List<Role>> qryByCondition(RoleConditionReq req);
}
