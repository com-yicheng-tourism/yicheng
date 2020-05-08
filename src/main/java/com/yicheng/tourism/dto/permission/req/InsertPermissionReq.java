package com.yicheng.tourism.dto.permission.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("新增权限请求参数")
@Data
public class InsertPermissionReq {

    @ApiModelProperty(value = "权限代码",dataType = "String")
    private String code;
    @ApiModelProperty(value = "权限名称",dataType = "String")
    private String name;
    @ApiModelProperty(value = "权限描述",dataType = "String")
    private String description;
}