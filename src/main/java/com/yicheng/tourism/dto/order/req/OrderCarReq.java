package com.yicheng.tourism.dto.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(value = "查询订单列表列表请求参数")
@Data
public class OrderCarReq {
    @ApiModelProperty(value = "店铺编号",dataType = "BigDecimal")
    private BigDecimal commodityPrice;
    @ApiModelProperty(value = "店铺编号",dataType = "String")
    private String storeNumber;
    @ApiModelProperty(value = "商品名称",dataType = "String")
    private String commodityName;
    @ApiModelProperty(value = "商品id",dataType = "String")
    private String commodityId;
    @ApiModelProperty(value = "密码",dataType = "String")
    private String password;
    @ApiModelProperty(value = "用户id",dataType = "String")
    private String userName;
}
