package com.yicheng.tourism.dto.comment.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReplyCommentReq implements Serializable {

    @ApiModelProperty("用户名")
    private String userId;
    @ApiModelProperty("评论id")
    private String commentId;
    @ApiModelProperty("商品id")
    private String commodityId;
    @ApiModelProperty("店铺id")
    private String shopId;
    @ApiModelProperty("评论内容")
    private String commentText;
    @ApiModelProperty("评论图片")
    private String commentImg;
}
