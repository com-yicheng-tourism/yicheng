package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.InsertPermissionReq;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.req.RoleInsertReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.Role;
import com.yicheng.tourism.service.PermissionService;
import com.yicheng.tourism.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "角色管理接口",description = "角色管理接口")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("查询角色列表")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<List<Role>> get(RoleConditionReq req){
        return roleService.qryByCondition(req);
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public BaseResponse<String> insert(RoleInsertReq req){
        return roleService.insert(req);
    }
}
