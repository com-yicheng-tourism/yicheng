package com.yicheng.tourism.enumerate;

public enum PermissionTypeEnum {
    INQUIRE("00000000","查询");
    String code;
    String name;

    PermissionTypeEnum() {
    }

    PermissionTypeEnum(String code, String name) {
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
