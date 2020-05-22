package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.QryCommentReq;
import com.yicheng.tourism.dto.comment.req.ReplyCommentReq;
import com.yicheng.tourism.dto.comment.req.SensitiveWordReq;
import com.yicheng.tourism.dto.comment.resp.QryCommentResp;
import com.yicheng.tourism.entity.SensitiveWord;

import java.util.List;

public interface SensitiveWordService {


    /**查询敏感词
     * @return
     */
    BaseResponse<PageInfo<SensitiveWord>> qry( SensitiveWordReq req);

    /**编辑敏感词
     * @return
     */
    BaseResponse<String> edit( SensitiveWordReq req);

    /**添加敏感词
     * @return
     */
    BaseResponse<String> insert( SensitiveWordReq req);
}
