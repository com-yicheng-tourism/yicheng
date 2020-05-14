package com.yicheng.tourism.dto.comment.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReplyCommentReq {

    @ApiModelProperty(value = "评论内容",dataType = "String")
    private String commentText;
}
