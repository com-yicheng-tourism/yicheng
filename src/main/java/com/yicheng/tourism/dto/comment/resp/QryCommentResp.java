package com.yicheng.tourism.dto.comment.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QryCommentResp {

    private String commentId;

    private String userId;

    private String nickName;

    private String userPic;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String createId;

    private String commentText;

    private String commentImg;

    private String notes;

    private List<ReplyResp> replyList;
}
