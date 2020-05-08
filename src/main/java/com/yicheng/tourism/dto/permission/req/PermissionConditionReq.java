package com.yicheng.tourism.dto.permission.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("权限根据调价查询请求参数")
@Data
public class PermissionConditionReq {

    @ApiModelProperty(value = "关键词",dataType = "String")
    private String keyWords;
}