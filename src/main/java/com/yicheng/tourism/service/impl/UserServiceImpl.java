package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.controller.VerCodeController;
import com.yicheng.tourism.dto.order.req.OrderCarReq;
import com.yicheng.tourism.dto.role.req.AssignRoleReq;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.dto.user.resp.UserQryResp;
import com.yicheng.tourism.entity.*;
import com.yicheng.tourism.enumerate.LoginTypeEnum;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.PermissionMapper;
import com.yicheng.tourism.mapper.RoleMapper;
import com.yicheng.tourism.mapper.UserMapper;
import com.yicheng.tourism.mapper.UserRoleMapper;
import com.yicheng.tourism.mapper.ext.OrderMapperExt;
import com.yicheng.tourism.mapper.ext.UserMapperExt;
import com.yicheng.tourism.mapper.ext.UserRoleMapperExt;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.service.VerCodeService;
import com.yicheng.tourism.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private VerCodeController verCodeController;
    @Autowired
    private VerCodeService verCodeService;
    @Autowired
    private UserMapperExt userMapperExt;
    @Autowired
    private UserRoleMapperExt userRoleMapperExt;
    @Autowired
    private OrderMapperExt orderMapperExt;

    private HashedCredentialsMatcher matcher;

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

//    @Override
//    public List<Permission> getPermission(String userId) {
//        List<Role> roleList = getRoleList(userId);
//        PermissionExample example = new PermissionExample();
//        PermissionExample.Criteria criteria = example.createCriteria();
//        List<String> permissionId=new ArrayList<>();
//        for (Role role : roleList){
//            permissionId.add(role.getId());
//        }
//        criteria.andIdIn(permissionId);
//        return permissionMapper.selectByExample(example);
//    }

    /**
     * 用户注册
     *
     * @param req
     * @param request
     * @return
     */
    @Override
    public BaseResponse<String> register(UserRegisterOrLoginReq req, HttpServletRequest request) {
        User tempUser = new User();
        if (req.getRegisterStep()==1){//第一步,验证账号密码
            String verCodeText = verCodeService.getVerCodeText(request);
            if (!verCodeText.equalsIgnoreCase(req.getVerificationCode())){
                return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
            }
            User user = userMapperExt.qryByUserName(req.getUserName());
            if (!StringUtils.isEmpty(user)){
                return new BaseResponse<>(RespStatusEnum.USED_USERNAME.getCode(),RespStatusEnum.USED_USERNAME.getMessage());
            }
            tempUser.setUserName(req.getUserName());
            tempUser.setUserPwd(MD5Util.encrypt(req.getUserPassword()));
            tempUser.setMail("");
            tempUser.setSerialId(UUIDUtil.get());
            tempUser.setType("3");
            tempUser.setProfilePic("headImage4.jpg");
            tempUser.setNickName("游客");
            tempUser.setBirthday(new Date());
            tempUser.setIpAddress(IpUtil.getIpAddr(request));
            tempUser.setIsLogout(true);
            int i = userMapper.insertSelective(tempUser);
            if (i!=0){
                return new BaseResponse<>(RespStatusEnum.REGISTER_TEMP_SUCCESS.getCode(),RespStatusEnum.REGISTER_TEMP_SUCCESS.getMessage(),"开始进行邮箱验证");
            }
        }
        if (req.getRegisterStep()==2){//第二步,邮箱验证
            String emailCode = SessionUtil.getEmailCode(req.getMail(), request);
            log.error("emailCode:{}",emailCode);
            if (StringUtils.isEmpty(emailCode)){
                return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
            }
            if (!emailCode.equalsIgnoreCase(req.getVerificationCode())){
                return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
            }
            //2.根据邮箱判断用户是否存在
            User userByMail = userMapperExt.qryByMail(req.getMail());
            if (!StringUtils.isEmpty(userByMail)){
                return new BaseResponse<>(RespStatusEnum.USED_EMAIL.getCode(),RespStatusEnum.USED_EMAIL.getMessage());
            }
            tempUser.setUserName(req.getUserName());
            tempUser.setMail(req.getMail());
            tempUser.setCreateTime(new Date());
            tempUser.setIpAddress(IpUtil.getIpAddr(request));
            int i = userMapperExt.updateByUsername(tempUser);
            if (i != 0){
                return new BaseResponse<>(RespStatusEnum.REGISTER_SUCCESS.getCode(),RespStatusEnum.REGISTER_SUCCESS.getMessage());
            }
        }
        return new BaseResponse<>(RespStatusEnum.REGISTER_FAIL.getCode(),RespStatusEnum.REGISTER_FAIL.getMessage());
    }

    /**
     * 用户登录
     * @param req  用户登录请求参数
     * @param request
     * @return
     */
    @Override
    public BaseResponse<UserQryResp> login(UserRegisterOrLoginReq req, HttpServletRequest request) {
        if (StringUtils.isEmpty(req.getLoginType())){
            return new BaseResponse<>(RespStatusEnum.LOGIN_TYPE_IS_NULL.getCode(),RespStatusEnum.LOGIN_TYPE_IS_NULL.getMessage());
        }
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        switch(LoginTypeEnum.valueOf(req.getLoginType())){
            case UNKNOWN://未知类型
                return new BaseResponse<>(RespStatusEnum.LOGIN_TYPE_UNKNOWN.getCode(),RespStatusEnum.LOGIN_TYPE_UNKNOWN.getMessage());
            case PASSWORD_LOGIN ://账号密码登录
                //1.验证码是否正确
                String verCodeText = verCodeService.getVerCodeText(request);
//                if (!verCodeText.equalsIgnoreCase(req.getVerificationCode())){
//                    return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
//                }
                User user = userMapperExt.qryByUserName(req.getUserName());
                //2.根据用户名判断是否存在该用户
                if (StringUtils.isEmpty(user)){
                    return new BaseResponse<>(RespStatusEnum.NO_EXISTS_USER.getCode(),RespStatusEnum.NO_EXISTS_USER.getMessage());
                }
                //3.验证密码是否正确
                if (!user.getUserPwd().equals(MD5Util.encrypt(req.getUserPassword()))){
                    return new BaseResponse<>(RespStatusEnum.PASSWORD_ERROR.getCode(),RespStatusEnum.PASSWORD_ERROR.getMessage());
                }
                valueOperations.set(user.getUserName(),user,9000, TimeUnit.SECONDS);
                request.getSession().setAttribute("userId",user);
                UserQryResp qryResp = new UserQryResp();
                BeanUtils.copyProperties(user,qryResp);
                qryResp.setProfilePic("http://localhost:8080/img/seekExperts?type=1&picName="+user.getProfilePic());
                return new BaseResponse<>(RespStatusEnum.LOGIN_SUCCESS.getCode(),RespStatusEnum.LOGIN_SUCCESS.getMessage(),qryResp);
            case EMAIL_LOGIN ://邮箱登录
                String emailCode = SessionUtil.getEmailCode("emailCode", request);
                if (!StringUtils.isEmpty(emailCode) && !emailCode.equalsIgnoreCase(req.getVerificationCode())){
                    return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
                }
                //2.根据邮箱判断用户是否存在
                User userByMail = userMapperExt.qryByMail(req.getMail());
                if (StringUtils.isEmpty(userByMail)){
                    return new BaseResponse<>(RespStatusEnum.NO_EXISTS_USER.getCode(),RespStatusEnum.NO_EXISTS_USER.getMessage());
                }

                valueOperations.set(userByMail.getUserName(),userByMail,9000, TimeUnit.SECONDS);
                request.getSession().setAttribute("userId",userByMail);
                UserQryResp qryResp1 = new UserQryResp();
                BeanUtils.copyProperties(userByMail,qryResp1);
                qryResp1.setProfilePic("http://localhost:8080/img/seekExperts?type=1&picName="+userByMail.getProfilePic());
                return new BaseResponse<>(RespStatusEnum.LOGIN_SUCCESS.getCode(),RespStatusEnum.LOGIN_SUCCESS.getMessage(),qryResp1);
            case PHONE_LOGIN ://手机号登录
                break;
            default :
                break;
        }
        return new BaseResponse<>(RespStatusEnum.LOGIN_FAIL.getCode(),RespStatusEnum.LOGIN_FAIL.getMessage());
    }

    /**
     * 获取用户详情
     *
     * @param serialCode
     * @return
     */
    @Override
    public BaseResponse<User> getDetail(String serialCode) {
        if (StringUtils.isEmpty(serialCode)){
            return new BaseResponse<>(RespStatusEnum.SERIAL_CODE_IS_NULL.getCode(),RespStatusEnum.SERIAL_CODE_IS_NULL.getMessage());
        }
        User user = userMapper.selectByPrimaryKey(serialCode);
        if (StringUtils.isEmpty(user)){
            return new BaseResponse<>(RespStatusEnum.NO_EXISTS_USER.getCode(),RespStatusEnum.NO_EXISTS_USER.getMessage());
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),user);
    }

    /**
     * 编辑/修改用户信息
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> edit(UpdateUserInfoReq req,HttpServletRequest request) {
        BaseResponse<User> verification = verification(request);
        User verificationData = verification.getData();
        if (StringUtils.isEmpty(req.getUserName())){
            return new BaseResponse<>(RespStatusEnum.SERIAL_CODE_IS_NULL.getCode(),RespStatusEnum.SERIAL_CODE_IS_NULL.getMessage());
        }
        String id = userMapperExt.qrySerialId(req.getUserName());
        if (StringUtils.isEmpty(id)){
            return new BaseResponse<>(RespStatusEnum.SERIAL_CODE_IS_NULL.getCode(),RespStatusEnum.SERIAL_CODE_IS_NULL.getMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(req,user);
        user.setSerialId(id);
        if (!StringUtils.isEmpty(req.getRole())){
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserIdEqualTo(req.getUserName());
            List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
            RoleExample roleExample = new RoleExample();
            RoleExample.Criteria criteria1 = roleExample.createCriteria();
            criteria1.andNameEqualTo(req.getRole());
            List<Role> roleList = roleMapper.selectByExample(roleExample);
            if (!CollectionUtils.isEmpty(userRoleList) && !CollectionUtils.isEmpty(roleList)){
                UserRole userRole = new UserRole();
                UserRole userRole1 = userRoleList.get(0);
                Role role = roleList.get(0);
                userRole.setSerialId(userRole1.getSerialId());
                userRole.setRoleId(role.getId());
                userRole.setModifyTime(new Date());
                userRole.setModifyId(verificationData.getUserName());
                userRoleMapper.updateByPrimaryKeySelective(userRole);
            }


        }
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"修改成功");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"修改失败");
    }

    /**
     * 按条件进行用户信息查询
     *
     * @param req   请求参数
     * @return 数据列表
     */
    @Override
    public BaseResponse<PageInfo<User>> qryByCondition(UserQryConditionReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        PageHelper.startPage(req.getPage(),req.getRows());
        List<User> users = userMapperExt.qryByCondition(req);
            users.forEach(user -> user.setProfilePic("http://localhost:8080/img/seekExperts?type=1&picName="+user.getProfilePic()));
            PageInfo<User> pageInfo = new PageInfo<>(users);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
//        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
    }

    /**
     * 为用户分配角色
     *
     * @param roleReq
     * @return
     */
    @Override
    public BaseResponse<String> assignRole(List<AssignRoleReq> roleReq,HttpServletRequest request) {
        List<UserRole> userRoles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleReq)){
            for (AssignRoleReq req : roleReq){
                UserRole userRole = new UserRole();
                userRole.setSerialId(UUIDUtil.get());
                userRole.setUserId(req.getUserId());
                userRole.setRoleId(req.getRoleId());
                userRole.setCreateTime(new Date());
                ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
                User user = (User) valueOperations.get(request.getSession().getAttribute("userId"));
                if (StringUtils.isEmpty(user)){
                    return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),RespStatusEnum.TOKEN_FAILURE.getMessage());
                }
                userRole.setCreateId(user.getUserName());
                userRole.setNotes(req.getNotes());
                userRoles.add(userRole);
            }
        }
        int i = userRoleMapperExt.insertBatch(userRoles);
        if (i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"角色分配成功");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"角色分配失败");
    }

    /**
     * 验证用户是否有对应api的访问权限
     *
     * @return
     */
    @Override
    public BaseResponse<User> verification(HttpServletRequest request) {
//        log.error("请求地址:{}",request.getRequestURI());
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        User userId = (User) request.getSession().getAttribute("userId");
        User user = (User) valueOperations.get(userId.getUserName());
        if (StringUtils.isEmpty(user)){
            return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
        }
        int i = userMapperExt.verification(user.getUserName(), request.getRequestURI().substring(0,request.getRequestURI().lastIndexOf("/")));
        if (i != 0 ){
            return new BaseResponse<>(RespStatusEnum.HAVING_PERMISSION.getCode(),RespStatusEnum.HAVING_PERMISSION.getMessage(),user);
        }
        return new BaseResponse<>(RespStatusEnum.NO_PERMISSION.getCode(),RespStatusEnum.NO_PERMISSION.getMessage());
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    @Override
    public User getToken(HttpServletRequest request) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        User userId = (User) request.getSession().getAttribute("userId");
        if (!StringUtils.isEmpty(userId)){
            User user = (User) valueOperations.get(userId.getUserName());
            if (!StringUtils.isEmpty(user)){
                return user;
            }
        }
        return null;
    }

    @Override
    public Object queryById(UpdateUserInfoReq req) {
        List<User> users = new ArrayList<>();
        try {
            users = userMapperExt.findById(req);

        } catch (Exception e) {
            return -1;
        }
        return users;
    }

    @Override
    public Object pasdVer(OrderCarReq req) {
        String pasd = userMapperExt.findByUserName(req.getUserName());
        Order order = new Order();
        order.setId(CreateTestDataUtil.createSerialId());
        order.setCommodityId(req.getCommodityId());
        order.setCommodityName(req.getCommodityName());
        order.setActrualPrice(req.getCommodityPrice().doubleValue());
        order.setCreateTime(CreateTestDataUtil.createTime());
        if (MD5Util.encrypt(req.getPassword()).equals(pasd)){
            userMapperExt.updatePriceByUserName(req);
            orderMapperExt.insert(order);

            return 0;
        } else {
            return -1;
        }

    }


}
