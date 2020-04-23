package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.req.RoleInsertReq;
import com.yicheng.tourism.entity.Role;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.RoleMapper;
import com.yicheng.tourism.mapper.ext.RoleMapperExt;
import com.yicheng.tourism.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMapperExt roleMapperExt;
    /**
     * 角色添加
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> insert(RoleInsertReq req) {
        Role role = new Role();
        BeanUtils.copyProperties(req,role);
        int i = roleMapper.insert(role);
        if (i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"插入成功");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"插入失败");
    }

    /**
     * 根据条件进行角色查询
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<List<Role>> qryByCondition(RoleConditionReq req) {
        List<Role> roles = roleMapperExt.qryByCondition(req);
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),roles);
    }
}
