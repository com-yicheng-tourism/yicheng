package com.yicheng.tourism.service;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import java.util.List;

public interface PermissionService {
    BaseResponse<List<Permission>> qryByCondition(PermissionConditionReq req);
}
