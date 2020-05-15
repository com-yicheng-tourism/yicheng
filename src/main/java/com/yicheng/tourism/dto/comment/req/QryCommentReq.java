package com.yicheng.tourism.dto.comment.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QryCommentReq {
    @ApiModelProperty("店铺id")
    private String shopId;
    @ApiModelProperty("商品id")
    private String commodityId;
}
