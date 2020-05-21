package com.yicheng.tourism.dto.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询订单列表列表请求参数")
@Data
public class OrderQueryReq {

    @ApiModelProperty(value = "查询第几页",dataType = "Integer")
    private Integer page;
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(value = "店铺编号",dataType = "String")
    private String storeNumber;
    @ApiModelProperty(value = "商品名称",dataType = "String")
    private String commodityName;
    @ApiModelProperty(value = "用户id",dataType = "String")
    private String userId;

}