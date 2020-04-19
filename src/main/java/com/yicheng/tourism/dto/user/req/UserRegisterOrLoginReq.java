package com.yicheng.tourism.dto.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户注册请求参数")
@Data
public class UserRegisterOrLoginReq {
    @ApiModelProperty("用户名/账号(长度在20以内)")
    private String userName;
    @ApiModelProperty("邮箱(最大长度为320)")
    private String mail;
    @ApiModelProperty("用户密码,传进来的是明文,存进数据库为密文")
    private String userPassword;
    @ApiModelProperty("验证码")
    private String verificationCode;
    @ApiModelProperty("登录类型(-1:未知类型;0:账号密码登录;1:邮箱登录;2:手机号登录)")
    private Integer loginType;
    @ApiModelProperty("注册步骤(1:账号密码验证;2:邮箱验证)")
    private Integer registerStep;
}
