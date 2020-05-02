package com.yicheng.tourism.mapper;

import com.alibaba.fastjson.JSON;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.entity.Role;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.mapper.ext.RoleMapperExt;
import com.yicheng.tourism.mapper.ext.UserMapperExt;
import com.yicheng.tourism.util.CreateTestDataUtil;
import com.yicheng.tourism.util.MD5Util;
import com.yicheng.tourism.util.SubListUtil;
import com.yicheng.tourism.util.UUIDUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapperExt userMapperExt;
    @Autowired
    private RoleMapperExt roleMapperExt;
    @Test
    public void  test01(){
        User user = userMapper.selectByPrimaryKey("123133");
        log.info("查到的信息:{}", JSON.toJSONString(user));
    }
    @Test
    public void test02(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        UserRegisterOrLoginReq req = new UserRegisterOrLoginReq();
        req.setUserName("1358485802");
        User user = userMapperExt.qryByUserName(req.getUserName());
        log.error("查询到的用户信息:{}",JSON.toJSONString(user));
        log.error("生日是:{}",JSON.toJSONString(format.format(user.getBirthday())));
    }

    @Test
    public void test03(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        UserQryConditionReq req = new UserQryConditionReq();
        req.setKeyWords("1358");
        List<User> users = userMapperExt.qryByCondition(req);
        log.error("用户列表:{}",JSON.toJSONString(users));
//        UserRegisterOrLoginReq req = new UserRegisterOrLoginReq();
//        req.setUserName("1358485802");
//        User user = userMapperExt.qryByUserName(req.getUserName());
//        log.error("查询到的用户信息:{}",JSON.toJSONString(user));
//        log.error("生日是:{}",JSON.toJSONString(format.format(user.getBirthday())));
    }

    @Test
    public void test04(){
        List<Role> roles = roleMapperExt.qryByUsername("1358485802");
        log.error("查询的角色列表为:{}",JSON.toJSONString(roles));
    }
    @Test
    public void test05(){
        List<User> users=new ArrayList<>();
        for (int i=0;i<1000;i++){
            User user = new User();
            user.setSerialId(UUIDUtil.get());
            user.setUserName(CreateTestDataUtil.createUserName());
            user.setMail(CreateTestDataUtil.createMail(user.getUserName()));
            user.setUserPwd(CreateTestDataUtil.createPassword());
            user.setNickName(CreateTestDataUtil.createNickName());
            user.setBirthday(CreateTestDataUtil.createBirthday());
            user.setUserPhone(CreateTestDataUtil.createUserPhone());
            user.setProfilePic(CreateTestDataUtil.createImage());
            user.setUserAddress(CreateTestDataUtil.createAddress());
            user.setType(CreateTestDataUtil.createType().toString());
            user.setIpAddress(CreateTestDataUtil.createIp());
            users.add(user);
        }
        if (!users.isEmpty()) {
            List<List<User>> lists = SubListUtil.subList(users, 200);
            for (List<User> list : lists) {
                userMapperExt.insertBatch(list);
//                log.error("生成的数据列表:{}",JSON.toJSONString(list));
            }
        }
    }
}
