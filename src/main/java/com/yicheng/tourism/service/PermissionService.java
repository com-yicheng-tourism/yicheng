package com.yicheng.tourism.service;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.InsertPermissionReq;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import java.util.List;

public interface PermissionService {


    /**根据条件筛选查询
     * @param req
     * @return
     */
    BaseResponse<List<Permission>> qryByCondition(PermissionConditionReq req);

    /**管线维护新增
     * @param req
     * @return
     */
    BaseResponse<String> insert(InsertPermissionReq req);
}
