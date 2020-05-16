package com.yicheng.tourism.dto.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "查询用户列表请求参数")
@Data
public class UserQryConditionReq {

    @ApiModelProperty(value = "查询第几页",dataType = "Integer")
    private Integer page;
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(value = "所属部门",dataType = "String")
    private String dept;
    @ApiModelProperty(value = "拥有权限",dataType = "String")
    private String permission;
    @ApiModelProperty(value = "角色",dataType = "String")
    private String role;
    @ApiModelProperty(value = "关键词",dataType = "String")
    private String keyWords;
    @ApiModelProperty(value = "类型",dataType = "String")
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期",dataType = "String")
    private String startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "类型",dataType = "String")
    private String endTime;
}
