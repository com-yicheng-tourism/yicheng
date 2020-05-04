package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.permission.req.InsertPermissionReq;
import com.yicheng.tourism.dto.permission.req.PermissionConditionReq;
import com.yicheng.tourism.dto.role.req.AssignPermissionReq;
import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.req.RoleInsertReq;
import com.yicheng.tourism.dto.role.resp.RoleResp;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.Role;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.service.PermissionService;
import com.yicheng.tourism.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "角色管理接口",description = "角色管理接口")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RoleService roleService;

    @ApiOperation("查询角色列表")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<List<Role>> get(RoleConditionReq req){
        return roleService.qryByCondition(req);
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse<String> insert(@RequestBody RoleInsertReq req){
        return roleService.insert(req);
    }

    @ApiOperation("为角色分配权限")
    @RequestMapping(value = "/assignPermission",method = RequestMethod.POST)
    public BaseResponse<String> assignPermission(@RequestBody List<AssignPermissionReq> req, HttpServletRequest request){

        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        User user = (User) valueOperations.get(request.getSession().getAttribute("userId"));
        if (StringUtils.isEmpty(user)){
            return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),RespStatusEnum.TOKEN_FAILURE.getMessage());
        }
        return roleService.assignPermission(req,user.getUserName());
    }
}
