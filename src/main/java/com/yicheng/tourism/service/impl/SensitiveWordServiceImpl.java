package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.comment.req.SensitiveWordReq;
import com.yicheng.tourism.entity.Commodity;
import com.yicheng.tourism.entity.SensitiveWord;
import com.yicheng.tourism.entity.SensitiveWordExample;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.SensitiveWordMapper;
import com.yicheng.tourism.service.SensitiveWordService;
import com.yicheng.tourism.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;
    /**
     * 查询敏感词
     *
     * @return
     */
    @Override
    public BaseResponse<PageInfo<SensitiveWord>> qry(SensitiveWordReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        SensitiveWordExample sensitiveWordExample = new SensitiveWordExample();
        PageHelper.startPage(req.getPage(),req.getRows());
        List<SensitiveWord> sensitiveWords = sensitiveWordMapper.selectByExample(sensitiveWordExample);
        PageInfo<SensitiveWord> pageInfo = new PageInfo<>(sensitiveWords);
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
    }

    /**
     * 编辑敏感词
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> edit(SensitiveWordReq req) {
        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setId(req.getId());
        sensitiveWord.setName(req.getName());
        sensitiveWord.setModifyTime(new Date());
        sensitiveWord.setModifyBy(req.getUserId());
        int i = sensitiveWordMapper.updateByPrimaryKeySelective(sensitiveWord);
        if ( i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"修改成功!");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"修改失败!");
    }

    /**
     * 添加敏感词
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse<String> insert(SensitiveWordReq req) {
        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setId(UUIDUtil.get());
        sensitiveWord.setName(req.getName());
        sensitiveWord.setCreateTime(new Date());
        sensitiveWord.setCreateBy(req.getUserId());
        int i = sensitiveWordMapper.insertSelective(sensitiveWord);
        if ( i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"修改成功!");
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"修改失败!");
    }
}
