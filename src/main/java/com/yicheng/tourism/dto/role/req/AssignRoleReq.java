package com.yicheng.tourism.dto.role.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("为用户分配角色请求参数")
@Data
public class AssignRoleReq {
    @ApiModelProperty(value = "权限id",dataType = "String")
    String roleId;
    @ApiModelProperty(value = "用户id",dataType = "String")
    String userId;
    @ApiModelProperty(value = "备注",dataType = "String")
    String notes;
}
