package com.yicheng.tourism.service;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;

public interface CommentService {

    /**回复评论
     * @param req
     * @return
     */
    BaseResponse<String> reply(ReplyCommentReq req);
}
