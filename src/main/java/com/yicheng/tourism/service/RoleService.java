package com.yicheng.tourism.service;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.role.req.AssignPermissionReq;
import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.req.RoleInsertReq;
import com.yicheng.tourism.dto.role.resp.RoleResp;

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
    BaseResponse<List<RoleResp>> qryByCondition(RoleConditionReq req);

    /**为角色分配权限
     * @param req
     * @return
     */
    BaseResponse<String> assignPermission(AssignPermissionReq req);
}
