package com.yicheng.tourism.service.impl;

import com.alibaba.fastjson.JSON;
import com.yicheng.tourism.dto.user.req.UserRegisterReq;
import com.yicheng.tourism.entity.*;
import com.yicheng.tourism.mapper.PermissionMapper;
import com.yicheng.tourism.mapper.RoleMapper;
import com.yicheng.tourism.mapper.UserMapper;
import com.yicheng.tourism.mapper.UserRoleMapper;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.util.MD5Util;
import com.yicheng.tourism.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    private HashedCredentialsMatcher matcher;
    @Override
    public void scanDir() {
        File file = new File("D:/data/vehicle/upload");
        String[] list = file.list();
        for (String s : list){
            log.info("扫描结果为:{}",s);
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andSerialIdEqualTo("0001");
        List<User> users = userMapper.selectByExample(example);
        log.info("返回的列表:{}", JSON.toJSONString(users));
    }

    @Override
    public List<Role> getRoleList(String userId) {
        UserRoleExample example1 = new UserRoleExample();
        UserRoleExample.Criteria criteria1 = example1.createCriteria();
        if (!StringUtils.isEmpty(userId)){
            criteria1.andUserIdEqualTo(userId);
        }
        List<UserRole> userRoleList = userRoleMapper.selectByExample(example1);
        List<String> roleIdList=new ArrayList<>();
        for (UserRole userRole : userRoleList){
            roleIdList.add(userRole.getRoleId());
        }
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if (null!=roleIdList && roleIdList.size()!=0){
            criteria.andIdIn(roleIdList);
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Permission> getPermission(String userId) {
        List<Role> roleList = getRoleList(userId);
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        List<String> permissionId=new ArrayList<>();
        for (Role role : roleList){
            permissionId.add(role.getId());
        }
        criteria.andIdIn(permissionId);
        return permissionMapper.selectByExample(example);
    }

//    @Override
//    public User getUserById(String userId) {
//        if (!StringUtils.isEmpty(userId)){
//            return userMapper.selectByPrimaryKey(userId);
//        }
//        return null;
//    }

    @Override
    public String register(UserRegisterReq req) {
        if (StringUtils.isEmpty(req.getUserName())){
            return "用户名/账号不能为空";
        }
        if (StringUtils.isEmpty(req.getUserPassword())){
            return "用户密码不能为空";
        }
        if (StringUtils.isEmpty(req.getMail())){
            return "用户邮箱不能为空";
        }
        User user = new User();
        user.setUserName(req.getUserName());
        user.setUserPwd(MD5Util.encrypt(req.getUserPassword()));
        user.seteMail(req.getMail());
        user.setSerialId(UUIDUtil.get());
        user.setIsLogout(true);
        log.info("待插入的信息:{}",JSON.toJSONString(user));
        int i = userMapper.insertSelective(user);
        if (i!=0){
            return "注册成功";
        }
        return "注册失败";
    }
}
