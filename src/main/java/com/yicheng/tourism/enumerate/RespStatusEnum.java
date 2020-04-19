package com.yicheng.tourism.enumerate;

public enum RespStatusEnum {
    UNKNOWN("999_999_999","未知错误"),
    //TODO 请求成功000开头
    SUCCESS("000_000_000","请求成功"),
    SERIAL_CODE_IS_NULL("000_000_001","主键信息不能为空"),
    //TODO 请求失败100开头
    FAIL("100_000_000","请求失败"),
    //TODO 用户相关枚举值中间为001
    NO_EXISTS_USER("000_001_000", "用户不存在"),
    USED_USERNAME("000_001_001", "用户名已被使用"),
    USED_EMAIL("000_001_002", "该邮箱已被使用"),
    LOGIN_TYPE_IS_NULL("000_001_003","登陆方式类型不能为空"),
    LOGIN_INFO_IS_NULL("000_001_004","登录用户信息为空"),
    LOGIN_TYPE_UNKNOWN("000_001_005","登陆类型未知"),
    VERIFY_CODE_ERROR("000_001_006","验证码错误"),
    PASSWORD_ERROR("000_001_007","密码错误"),
    LOGIN_SUCCESS("000_001_008","登陆成功"),
    LOGIN_FAIL("000_001_009","登录失败"),
    REGISTER_FAIL("000_001_010","注册失败"),
    REGISTER_SUCCESS("000_001_011","注册成功"),
    REGISTER_TEMP_SUCCESS("000_001_012","注册暂时成功,进入下一步验证");

    String code;
    String message;

    RespStatusEnum() {
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    RespStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
