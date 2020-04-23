package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.InsertPermissionReq;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.PermissionMapper;
import com.yicheng.tourism.mapper.ext.PermissionMapperExt;
import com.yicheng.tourism.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PermissionMapperExt permissionMapperExt;
    @Override
    public BaseResponse<List<Permission>> qryByCondition(PermissionConditionReq req) {
        List<Permission> permissionList = permissionMapperExt.qryByCondition(req);
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),permissionList);
    }

    /**
     * 管线维护新增
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> insert(InsertPermissionReq req) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(req,permission);
        int insert = permissionMapper.insert(permission);
        if (insert != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"权限插入成功!");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"权限插入失败!");
    }


}
