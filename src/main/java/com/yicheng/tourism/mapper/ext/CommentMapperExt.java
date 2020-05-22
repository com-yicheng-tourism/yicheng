package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.comment.req.QryCommentReq;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.dto.comment.resp.QryCommentResp;
import com.yicheng.tourism.dto.comment.resp.ReplyResp;
import com.yicheng.tourism.dto.coupon.req.QryCouponReq;
import com.yicheng.tourism.entity.Comment;
import com.yicheng.tourism.entity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapperExt {

    /**根据条件查询优惠券信息
     * @param req
     * @return
     */
   List<QryCommentResp> qry(@Param("c") QryCommentReq req);

    /**查询评论的回复
     * @param commentId
     * @return
     */
   List<ReplyResp> qryReply(String commentId);

    void insert( @Param("c") Comment comment);
}
