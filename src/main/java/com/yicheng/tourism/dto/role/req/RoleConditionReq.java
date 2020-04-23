package com.yicheng.tourism.dto.role.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("角色条件查询请求参数")
@Data
public class RoleConditionReq {

    @ApiModelProperty(value = "关键词",dataType = "String")
    private String keyWords;
}
