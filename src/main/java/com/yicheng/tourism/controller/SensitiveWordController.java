package com.yicheng.tourism.controller;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.QryCommentReq;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.dto.comment.req.SensitiveWordReq;
import com.yicheng.tourism.dto.comment.resp.QryCommentResp;
import com.yicheng.tourism.entity.SensitiveWord;
import com.yicheng.tourism.service.CommentService;
import com.yicheng.tourism.service.SensitiveWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "敏感词管理",description = "敏感词管理")
@Slf4j
@RestController
@RequestMapping("/sensitiveWord")
public class SensitiveWordController {

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @ApiOperation("查看敏感词")
    @RequestMapping(value = "/qry",method = RequestMethod.GET)
    public BaseResponse<PageInfo<SensitiveWord>> qry(SensitiveWordReq req){
        return sensitiveWordService.qry(req);
    }

    @ApiOperation("编辑敏感词")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public BaseResponse<String> edit(@RequestBody SensitiveWordReq req){
        return sensitiveWordService.edit(req);
    }

    @ApiOperation("添加敏感词")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResponse<String> insert(@RequestBody SensitiveWordReq req){
        return sensitiveWordService.insert(req);
    }
}
