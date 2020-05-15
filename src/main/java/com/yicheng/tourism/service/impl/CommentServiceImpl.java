package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.QryCommentReq;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.dto.comment.resp.QryCommentResp;
import com.yicheng.tourism.dto.comment.resp.ReplyResp;
import com.yicheng.tourism.entity.Comment;
import com.yicheng.tourism.entity.Reply;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.ReplyMapper;
import com.yicheng.tourism.mapper.ext.CommentMapperExt;
import com.yicheng.tourism.service.CommentService;
import com.yicheng.tourism.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapperExt commentMapperExt;
    @Autowired
    private ReplyMapper replyMapper;
    /**
     * 回复评论
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> reply(ReplyCommentReq req) {
//        Comment comment = new Comment();
        Reply reply = new Reply();
        reply.setSerialId(UUIDUtil.get());
        reply.setUserId(req.getUserId());
        reply.setCommentId(req.getCommentId());
        reply.setCommentText(req.getCommentText());
        reply.setCreateId(req.getUserId());
        reply.setCreateTime(new Date());
        int i = replyMapper.insertSelective(reply);
        if (i != 0 ){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"评论成功");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"评论失败");
    }

    /**
     * 根据条件查询评论信息
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<List<QryCommentResp>> qry(QryCommentReq req) {
        List<QryCommentResp> comments = commentMapperExt.qry(req);
        comments.forEach(comment -> {
            comment.setUserPic("http://localhost:8080/img/seekExperts?type=1&picName="+comment.getUserPic());
            comment.setCommentImg("http://localhost:8080/img/seekExperts?type=3&picName="+comment.getCommentImg());
            List<ReplyResp> replyResp = commentMapperExt.qryReply(comment.getCommentId());
            replyResp.forEach(reply -> {
                reply.setUserPic("http://localhost:8080/img/seekExperts?type=1&picName="+reply.getUserPic());
            });
            comment.setReplyList(replyResp);
        });
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),comments);
    }
}
