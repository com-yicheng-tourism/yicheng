package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.InsertPermissionReq;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "权限管理接口",description = "权限管理接口")
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("查询权限列表")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<List<Permission>> get(PermissionConditionReq req){
        return permissionService.qryByCondition(req);
    }

    @ApiOperation("添加权限")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse<String> insert(@RequestBody InsertPermissionReq req){
        return permissionService.insert(req);
    }
}
