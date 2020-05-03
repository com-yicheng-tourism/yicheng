package com.yicheng.tourism.dto.store.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询商铺列表请求参数")
@Data
public class StoreQueryReq {

    @ApiModelProperty(value = "查询第几页",dataType = "Integer")
    private Integer page;
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer rows;

    @ApiModelProperty(value = "店铺名称",dataType = "String")
    private String storeName;

    @ApiModelProperty(value = "创建人",dataType = "String")
    private String createBy;
}
