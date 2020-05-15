package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.QryCommentReq;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.dto.comment.resp.QryCommentResp;
import com.yicheng.tourism.entity.Comment;
import com.yicheng.tourism.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "评论管理接口",description = "评论管理接口")
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @ApiOperation("回复评论")
    @RequestMapping(value = "/reply",method = RequestMethod.GET)
    public BaseResponse<String> reply( ReplyCommentReq req){
        return commentService.reply(req);
    }
    @ApiOperation("查看评论")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<List<QryCommentResp>> qry(QryCommentReq req){
        return commentService.qry(req);
    }
}
