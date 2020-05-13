package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.commodity;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.ext.CommodityMapperExt;
import com.yicheng.tourism.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapperExt commodityMapperExt;

    /**
     * 返回商品页面信息
     * @param req
     * @return
     */
    @Override
    public BaseResponse<PageInfo<commodity>> findPage(CommodityQueryReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        PageHelper.startPage(req.getPage(),req.getRows());
        List<commodity> commodities = commodityMapperExt.qryByCondition(req);
        if (!CollectionUtils.isEmpty(commodities)){
            PageInfo<commodity> pageInfo = new PageInfo<>(commodities);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
    }

    /**
     * 新增商品信息
     * @param com
     * @return
     */
    @Override
    public Object insertCommodity(commodity com) {
        try {
            commodityMapperExt.insert(com);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @Override
    public Object deleteCommodity(commodity com) {
        try {
            commodityMapperExt.delete(com);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    /**
     * 更新商品信息
     * @param com
     * @return
     */
    @Override
    public Object updateCommodity(commodity com) {
        try {
            commodityMapperExt.update(com);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @Override
    public Object findById(commodity com) {
        List<commodity> commodities = new ArrayList<>();
        try {
            commodities = commodityMapperExt.findById(com);
        } catch (Exception e) {
            return -1;
        }
        return commodities;
    }
}
