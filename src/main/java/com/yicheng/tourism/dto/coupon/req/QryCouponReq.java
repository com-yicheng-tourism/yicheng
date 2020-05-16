package com.yicheng.tourism.dto.coupon.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询优惠券列表列表请求参数")
@Data
public class QryCouponReq {
    @ApiModelProperty(value = "查询第几页",dataType = "Integer")
    private Integer page;
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(value = "店铺id",dataType = "String")
    private String storeId;

}
