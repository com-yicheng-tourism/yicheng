package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.controller.VerCodeController;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.entity.*;
import com.yicheng.tourism.enumerate.LoginTypeEnum;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.PermissionMapper;
import com.yicheng.tourism.mapper.RoleMapper;
import com.yicheng.tourism.mapper.UserMapper;
import com.yicheng.tourism.mapper.UserRoleMapper;
import com.yicheng.tourism.mapper.ext.UserMapperExt;
import com.yicheng.tourism.model.PageParam;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.service.VerCodeService;
import com.yicheng.tourism.util.MD5Util;
import com.yicheng.tourism.util.SessionUtil;
import com.yicheng.tourism.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private VerCodeController verCodeController;
    @Autowired
    private VerCodeService verCodeService;
    @Autowired
    private UserMapperExt userMapperExt;
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
            tempUser.setIsLogout(true);
            int i = userMapper.insertSelective(tempUser);
            if (i!=0){
                return new BaseResponse<>(RespStatusEnum.REGISTER_TEMP_SUCCESS.getCode(),RespStatusEnum.REGISTER_TEMP_SUCCESS.getMessage(),"开始进行邮箱验证");
            }
        }
        if (req.getRegisterStep()==2){//第二步,邮箱验证
            String emailCode = SessionUtil.getEmailCode("emailCode", request);
            if (!StringUtils.isEmpty(emailCode) && !emailCode.equalsIgnoreCase(req.getVerificationCode())){
                return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
            }
            //2.根据邮箱判断用户是否存在
            User userByMail = userMapperExt.qryByMail(req.getMail());
            if (!StringUtils.isEmpty(userByMail)){
                return new BaseResponse<>(RespStatusEnum.USED_EMAIL.getCode(),RespStatusEnum.USED_EMAIL.getMessage());
            }
            tempUser.setMail(req.getMail());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserNameEqualTo(req.getUserName());
            int i = userMapper.updateByExampleSelective(tempUser, userExample);
            if (i != 0){
                return new BaseResponse<>(RespStatusEnum.REGISTER_SUCCESS.getCode(),RespStatusEnum.REGISTER_SUCCESS.getMessage());
            }
            return new BaseResponse<>(RespStatusEnum.LOGIN_SUCCESS.getCode(),RespStatusEnum.LOGIN_SUCCESS.getMessage());
        }
        return new BaseResponse<>(RespStatusEnum.REGISTER_FAIL.getCode(),RespStatusEnum.REGISTER_FAIL.getMessage());
    }

//    @Override
//    public String register(UserRegisterOrLoginReq req, HttpServletRequest request) {
//        UserExample example = new UserExample();
//        UserExample.Criteria criteria = example.createCriteria();
//        if(!StringUtils.isEmpty(req.getMail())){
//            criteria.andEMailEqualTo(req.getMail());
//            List<User> users = userMapper.selectByExample(example);
//            if (!CollectionUtils.isEmpty(users)){
//                return "邮箱已被使用";
//            }else {
//                log.info("准备执行session");
//                String emailCode = SessionUtil.getEmailCode("emailCode", request);
//                log.info("邮箱验证码:{}",emailCode);
//                if (!emailCode.equalsIgnoreCase(req.getVerificationCode())){
//                    return "验证码错误";
//                }else {
//                    User user = new User();
//                    user.seteMail(req.getMail());
//                    UserExample userExample = new UserExample();
//                    UserExample.Criteria criteria1 = userExample.createCriteria();
//                    criteria1.andUserNameEqualTo(req.getUserName());
////                    int i = userMapper.updateByExample(user, userExample);
//                    int i = userMapper.updateByExampleSelective(user, userExample);
//                    if (i!=0){
//                        return "注册成功";
//                    }else {
//                        return "注册失败";
//                    }
//                }
//            }
//
//        }else {
//            if (!StringUtils.isEmpty(req.getUserName())){
//                criteria.andUserNameEqualTo(req.getUserName());
//                List<User> users = userMapper.selectByExample(example);
//                if (!CollectionUtils.isEmpty(users)){
//                    return "账号已存在!";
//                }
//            }
//            if (!StringUtils.isEmpty(req.getVerificationCode())){
//                String verCodeText = verCodeService.getVerCodeText(request);
//                if (!verCodeText.equalsIgnoreCase(req.getVerificationCode())){
//                    return "验证码错误";
//                }
//            }
//            User user = new User();
//            user.setUserName(req.getUserName());
//            user.setUserPwd(MD5Util.encrypt(req.getUserPassword()));
//            user.seteMail("");
//            user.setSerialId(UUIDUtil.get());
//            user.setIsLogout(true);
//            log.info("待插入的信息:{}",JSON.toJSONString(user));
//            int i = userMapper.insertSelective(user);
//            if (i!=0){
//                return "进行邮箱验证";
//            }
//        }
//        return "注册失败";
//    }

    /**
     * 用户登录
     * @param req  用户登录请求参数
     * @param request
     * @return
     */
    @Override
    public BaseResponse<String> login(UserRegisterOrLoginReq req, HttpServletRequest request) {
        if (StringUtils.isEmpty(req.getLoginType())){
            return new BaseResponse<>(RespStatusEnum.LOGIN_TYPE_IS_NULL.getCode(),RespStatusEnum.LOGIN_TYPE_IS_NULL.getMessage());
        }
        switch(LoginTypeEnum.valueOf(req.getLoginType())){
            case UNKNOWN://未知类型
                return new BaseResponse<>(RespStatusEnum.LOGIN_TYPE_UNKNOWN.getCode(),RespStatusEnum.LOGIN_TYPE_UNKNOWN.getMessage());
            case PASSWORD_LOGIN ://账号密码登录
                //1.验证码是否正确
                String verCodeText = verCodeService.getVerCodeText(request);
                if (!verCodeText.equalsIgnoreCase(req.getVerificationCode())){
                    return new BaseResponse<>(RespStatusEnum.VERIFY_CODE_ERROR.getCode(),RespStatusEnum.VERIFY_CODE_ERROR.getMessage());
                }
                User user = userMapperExt.qryByUserName(req.getUserName());
                //2.根据用户名判断是否存在该用户
                if (StringUtils.isEmpty(user)){
                    return new BaseResponse<>(RespStatusEnum.NO_EXISTS_USER.getCode(),RespStatusEnum.NO_EXISTS_USER.getMessage());
                }
                //3.验证密码是否正确
                if (!user.getUserPwd().equals(MD5Util.encrypt(req.getUserPassword()))){
                    return new BaseResponse<>(RespStatusEnum.PASSWORD_ERROR.getCode(),RespStatusEnum.PASSWORD_ERROR.getMessage());
                }
                return new BaseResponse<>(RespStatusEnum.LOGIN_SUCCESS.getCode(),RespStatusEnum.LOGIN_SUCCESS.getMessage());
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
                return new BaseResponse<>(RespStatusEnum.LOGIN_SUCCESS.getCode(),RespStatusEnum.LOGIN_SUCCESS.getMessage());
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
    public BaseResponse<String> edit(UpdateUserInfoReq req) {
        if (StringUtils.isEmpty(req.getSerialId())){
            return new BaseResponse<>(RespStatusEnum.SERIAL_CODE_IS_NULL.getCode(),RespStatusEnum.SERIAL_CODE_IS_NULL.getMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(req,user);
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
     * @param param 分页参数
     * @return 数据列表
     */
    @Override
    public PageInfo<User> qryByCondition(UserQryReq req, PageParam param) {
        PageInfo<User> resultPage=new PageInfo<>();
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            resultPage.setPageNum(1);
            resultPage.setPages(1);
            return resultPage;
        }
        PageInfo<User> userPageInfo = new PageInfo<>(users);

        return userPageInfo;
    }


}
