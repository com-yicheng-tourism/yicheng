package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.InsertPermissionReq;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.PermissionExample;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.PermissionMapper;
import com.yicheng.tourism.mapper.ext.PermissionMapperExt;
import com.yicheng.tourism.service.PermissionService;
import com.yicheng.tourism.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final Integer PERMISSION_CODE_LENGTH=8;
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

        if (StringUtils.isEmpty(req.getCode())){
            return new BaseResponse<>(RespStatusEnum.PERMISSION_CODE_IS_NULL.getCode(),RespStatusEnum.PERMISSION_CODE_IS_NULL.getMessage());
        }
        if (req.getCode().length() != PERMISSION_CODE_LENGTH){
            return new BaseResponse<>(RespStatusEnum.PERMISSION_CODE_LENGTH_ERROR.getCode(),RespStatusEnum.PERMISSION_CODE_LENGTH_ERROR.getMessage());
        }
        if (StringUtils.isEmpty(req.getName())){
            return new BaseResponse<>(RespStatusEnum.PERMISSION_NAME_IS_NULL.getCode(),RespStatusEnum.PERMISSION_NAME_IS_NULL.getMessage());
        }
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria permissionExampleCriteria = permissionExample.createCriteria();
        permissionExampleCriteria.andCodeEqualTo(req.getCode());
        long l = permissionMapper.countByExample(permissionExample);
        if (l != 0){
            return new BaseResponse<>(RespStatusEnum.PERMISSION_EXISTS.getCode(),RespStatusEnum.PERMISSION_EXISTS.getMessage());
        }
        Permission permission = new Permission();
        permission.setId(UUIDUtil.get());
        permission.setName(req.getName());
        permission.setCode(req.getCode());
        permission.setCreateTime(new Date());
        permission.setDescription(req.getDescription());
        int insert = permissionMapper.insert(permission);
        if (insert != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"权限插入成功!");
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"权限插入失败!");
    }


}
