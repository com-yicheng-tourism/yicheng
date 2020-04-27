package com.yicheng.tourism.enumerate;

public enum RoleTypeEnum {
    VISITORS("00000000","访客");
    String code;
    String name;

    RoleTypeEnum() {
    }

    RoleTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
