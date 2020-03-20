package com.yicheng.tourism.service;

import com.yicheng.tourism.dto.user.req.UserLoginReq;
import com.yicheng.tourism.dto.user.req.UserRegisterReq;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.Role;
import com.yicheng.tourism.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    void scanDir();
    List<Role> getRoleList(String userId);
    List<Permission> getPermission(String userId);
//    User getUserById(String userId);
    String register(UserRegisterReq req, HttpServletRequest request);
}
