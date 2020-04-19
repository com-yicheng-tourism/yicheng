package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public BaseResponse<List<Permission>> qryByCondition(PermissionConditionReq req) {
        return null;
    }
}
