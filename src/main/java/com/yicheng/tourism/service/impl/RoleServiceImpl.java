package com.yicheng.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.role.resp.RolePermissionResp;
import com.yicheng.tourism.dto.role.req.AssignPermissionReq;
import com.yicheng.tourism.dto.role.req.RoleConditionReq;
import com.yicheng.tourism.dto.role.req.RoleInsertReq;
import com.yicheng.tourism.entity.*;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.RoleMapper;
import com.yicheng.tourism.mapper.RolePermissionMapper;
import com.yicheng.tourism.mapper.ext.NavMapperExt;
import com.yicheng.tourism.mapper.ext.PermissionMapperExt;
import com.yicheng.tourism.mapper.ext.RoleMapperExt;
import com.yicheng.tourism.mapper.ext.RolePermissionMapperExt;
import com.yicheng.tourism.service.RoleService;
import com.yicheng.tourism.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private static final Integer ROLE_CODE_LENGTH=8;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMapperExt roleMapperExt;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private RolePermissionMapperExt rolePermissionMapperExt;
    @Autowired
    private PermissionMapperExt permissionMapperExt;
    @Autowired
    private NavMapperExt navMapperExt;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 角色添加
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> insert(RoleInsertReq req) {
        Role role = new Role();
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (StringUtils.isEmpty(req.getCode()) || req.getCode().length() != ROLE_CODE_LENGTH){
            return new BaseResponse<>(RespStatusEnum.ROLE_CODE_IS_NULL.getCode(),RespStatusEnum.ROLE_CODE_IS_NULL.getMessage());
        }
//        for (Role r : roles){
//            if (req.getCode().equals(r.getCode())) {
//                return new BaseResponse<>(RespStatusEnum.ROLE_EXISTS.getCode(), RespStatusEnum.ROLE_EXISTS.getMessage(), "此角色信息已经存在,请核实!");
//            }
//        }
        role.setDescription(StringUtils.isEmpty(req.getDescription()) ? "" : req.getDescription());
        role.setId(UUIDUtil.get());
        role.setCreateTime(new Date());
        if (StringUtils.isEmpty(req.getName())){
            return new BaseResponse<>(RespStatusEnum.ROLE_NAME_IS_NULL.getCode(),RespStatusEnum.ROLE_NAME_IS_NULL.getMessage());
        }
        role.setName(req.getName());
//        role.setCode(req.getCode());
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

    /**
     * 为角色分配权限
     *
     * @param reqs
     * @return
     */
    @Override
    public BaseResponse<String> assignPermission(List<AssignPermissionReq> reqs,String username) {
//        List<String> roleIdList =new ArrayList<>();
//        List<String> navIdList =new ArrayList<>();
//        List<String> permissionIdList =new ArrayList<>();
//        if (!CollectionUtils.isEmpty(reqs)){
//            for (AssignPermissionReq req : reqs){
//                if (req.getId().startsWith("10")){
//                    roleIdList.add(req.getId());
//                }
//                if (req.getId().startsWith("20")){
//                    navIdList.add(req.getId());
//                }
//                if (req.getId().startsWith("30")){
//                    permissionIdList.add(req.getId());
//                }
//            }
//            List<Nav> navList = navMapperExt.qryByRole(roleIdList);
////            List<> delList
//            boolean flag =true;
//            for (Nav nav :navList){
//                for (String navId :navIdList){
//                    if (nav.getId() .equals(navId)){
//                        flag=false;
//                        continue;
//                    }
//                }
//                if (flag){
//
//                }
//            }
//        }
//        List<RolePermission> rolePermissions = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(reqs)){
//            for (AssignPermissionReq req : reqs){
//                if (StringUtils.isEmpty(req.getRoleId())){
//                    return new BaseResponse<>(RespStatusEnum.ROLE_ID_IS_NULL.getCode(),RespStatusEnum.ROLE_ID_IS_NULL.getMessage());
//                }
//                if (StringUtils.isEmpty(req.getPermissionId())){
//                    return new BaseResponse<>(RespStatusEnum.PERMISSION_ID_IS_NULL.getCode(),RespStatusEnum.PERMISSION_ID_IS_NULL.getMessage());
//                }
//                RolePermission rolePermission = new RolePermission();
//                rolePermission.setSerialId(UUIDUtil.get());
//                rolePermission.setRoleId(req.getRoleId());
//                rolePermission.setPermissionId(req.getPermissionId());
//                rolePermission.setCreateTime(new Date());
//
//                rolePermission.setCreateId(username);
//                rolePermission.setNotes(req.getNotes());
//                rolePermissions.add(rolePermission);
//            }
//        }
//        int i = rolePermissionMapperExt.insertBatch(rolePermissions);
////        int i = rolePermissionMapper.insert(rolePermission);
//        if (i != 0){
//            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"权限分配成功");
//        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"权限分配成功");
    }

    /**
     * 根据条件筛选查询
     *
     * @return
     */
    @Override
    public BaseResponse<List<RolePermissionResp>> getRolePermission() {
        List<RolePermissionResp> rolePermissionResp = new ArrayList<>();
        List<Role> rolePermissionResps = roleMapperExt.qryAll();
        List<RolePermissionResp> rolePermission = rolePermissionResps.stream().map(RolePermissionResp::new).collect(Collectors.toList());
//        List<String> roleId = new ArrayList<>();
//        rolePermissionResps.forEach(role -> {
//            roleId.add(role.getId());
//        });
//        List<Nav> navs = navMapperExt.qryByRole(roleId);
        List<Nav> navs = navMapperExt.qryAll();
        List<Nav> navList = new ArrayList<>();

        for (int i =0; i< rolePermissionResps.size();i++){
            for (int j=0;j<navs.size();j++){
                Nav nav = new Nav();
                BeanUtils.copyProperties(navs.get(j),nav);
                nav.setPid(rolePermissionResps.get(i).getId());
                navList.add(nav);
            }
        }
        List<RolePermissionResp> navLists = navList.stream().map(RolePermissionResp::new).collect(Collectors.toList());
//        List<Permission> permissionList = permissionMapperExt.qryByRole(roleId);
        List<Permission> permissionList = permissionMapperExt.qryAll();
        List<Permission> permissions = new ArrayList<>();
        for (int i =0; i< navs.size();i++){
            for (int j=0;j<permissionList.size();j++){
                Permission permission = new Permission();
                BeanUtils.copyProperties(permissionList.get(j),permission);
                permission.setPid(navs.get(i).getId());
                permissions.add(permission);
            }

        }
        List<RolePermissionResp> permissionResps = permissions.stream().map(RolePermissionResp::new).collect(Collectors.toList());

        rolePermissionResp.addAll(rolePermission);
        rolePermissionResp.addAll(navLists);
        rolePermissionResp.addAll(permissionResps);
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),rolePermissionResp);
    }
}
