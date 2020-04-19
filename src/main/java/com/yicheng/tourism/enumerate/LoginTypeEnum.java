package com.yicheng.tourism.enumerate;

public enum LoginTypeEnum {
    UNKNOWN("未知类型",-1),
    PASSWORD_LOGIN("账号密码登录",0),
    EMAIL_LOGIN("邮箱登录验证",1),
    PHONE_LOGIN("手机号登录",2);

    String name;
    Integer value;

    LoginTypeEnum() {
    }

    LoginTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public Integer getValue(){
        return value;
    }

    public static LoginTypeEnum valueOf(Integer value){
        for (LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()){
            if (loginTypeEnum.value.equals(value)) {
                return loginTypeEnum;
            }
        }
        return LoginTypeEnum.UNKNOWN;
    }
}
