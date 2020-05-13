package com.yicheng.tourism.dto.coupon.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "优惠券插入参数")
@Data
public class InsertCouponReq {

    @ApiModelProperty(value = "优惠券名称",dataType = "String")
    private String name;

    @ApiModelProperty(value = "店铺id",dataType = "String")
    private String storeId;

    @ApiModelProperty(value = "使用场景(1:指定店铺使用;2:所有店铺使用)",dataType = "String")
    private String useType;

    @ApiModelProperty(value = "优惠类型(1:满减;2立减;3:折扣)",dataType = "String")
    private String couponType;

    @ApiModelProperty(value = "类型为满减时需要达到多少开始满减",dataType = "Double")
    private Double reachedAmount;

    @ApiModelProperty(value = "类型为满减时减多少",dataType = "Double")
    private Double minusAmount;

    @ApiModelProperty(value = "类型为立减时减多少,最大为当前需要支付的金额",dataType = "Double")
    private Double reduceAmount;

    @ApiModelProperty(value = "类型为折扣时的折扣率",dataType = "Double")
    private Double discount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生效时按(yyyy-MM-dd HH:mm:ss)")
    private Date effectiveTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "失效时间(yyyy-MM-dd HH:mm:ss)")
    private Date expirationTime;

    @ApiModelProperty(value = "备注",dataType = "String")
    private String remark;
}
