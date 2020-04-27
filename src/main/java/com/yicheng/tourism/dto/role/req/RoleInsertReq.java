package com.yicheng.tourism.dto.role.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("角色添加请求参数")
@Data
public class RoleInsertReq {

    @ApiModelProperty(value = "角色名称",dataType = "String")
    private String name;

    @ApiModelProperty(value = "角色描述",dataType = "String")
    private String description;

    @ApiModelProperty(value = "角色代码",dataType = "String")
    private String code;
}
