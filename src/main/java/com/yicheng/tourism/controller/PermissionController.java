package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api("权限管理接口")
@Slf4j
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @ApiOperation("查询权限列表")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<List<Permission>> get(PermissionConditionReq req){
        return permissionService.qryByCondition(req);
    }
}
