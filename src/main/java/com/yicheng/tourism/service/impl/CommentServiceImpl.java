package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    /**
     * 回复评论
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> reply(ReplyCommentReq req) {
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage());
    }
}
