package com.yicheng.tourism.dto.comment.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询商品列表列表请求参数")
@Data
public class SensitiveWordReq {

    @ApiModelProperty(value = "查询第几页",dataType = "Integer")
    private Integer page;
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(value = "敏感词id",dataType = "String")
    private String id;
    @ApiModelProperty(value = "敏感词",dataType = "String")
    private String name;
    @ApiModelProperty(value = "操作人",dataType = "String")
    private String userId;


}
