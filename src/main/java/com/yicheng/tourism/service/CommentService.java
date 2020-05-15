package com.yicheng.tourism.service;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.QryCommentReq;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.dto.comment.resp.QryCommentResp;
import com.yicheng.tourism.entity.Comment;

import java.util.List;

public interface CommentService {

    /**回复评论
     * @param req
     * @return
     */
    BaseResponse<String> reply(ReplyCommentReq req);

    /**根据条件查询评论信息
     * @param req
     * @return
     */
    BaseResponse<List<QryCommentResp>> qry(QryCommentReq req);
}
