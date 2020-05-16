package com.yicheng.tourism.dto.role.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("为角色分配权限请求参数")
@Data
public class AssignPermissionReq {
    @ApiModelProperty(value = "权限id",dataType = "String")
    String roleId;
    @ApiModelProperty(value = "权限id",dataType = "String")
    String permissionId;
    @ApiModelProperty(value = "备注",dataType = "String")
    String notes;
    @ApiModelProperty(value = "主键",dataType = "String")
    String id;
}
