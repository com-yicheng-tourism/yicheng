package com.yicheng.tourism.service;

import com.alibaba.fastjson.JSON;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@Slf4j
@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;
    @Test
    public void test01(){
        String s="1358485802";
        List<Role> roleList = userService.getRoleList(s);
        roleList.forEach((role)->{log.info("查到的:"+s+"对应的角色信息为:"+ JSON.toJSONString(roleList));});
    }
    @Test
    public void test02(){
        String s="1358485802";
        List<Permission> permissionList = userService.getPermission(s);
        permissionList.forEach((permission)->{log.info("查到的:"+s+"对应的权限信息为:"+JSON.toJSONString(permissionList));});
    }

}
