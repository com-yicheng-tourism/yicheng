package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.Commodity;
import com.yicheng.tourism.entity.StoreCommodity;
import com.yicheng.tourism.entity.UserStore;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.ext.CommodityMapperExt;
import com.yicheng.tourism.mapper.ext.StoreCommodityMapperExt;
import com.yicheng.tourism.mapper.ext.UserStoreMapperExt;
import com.yicheng.tourism.service.CommodityService;
import com.yicheng.tourism.util.CreateTestDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapperExt commodityMapperExt;

    @Autowired
    private StoreCommodityMapperExt storeCommodityMapperExt;

    /**
     * 返回商品页面信息
     * @param req
     * @return
     */
    @Override
    public BaseResponse<PageInfo<Commodity>> findPage(CommodityQueryReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        List<Commodity> commodities=null;
        PageHelper.startPage(req.getPage(),req.getRows());
        if (StringUtils.isEmpty(req.getUserId())){
            commodities = commodityMapperExt.qryByCondition(req);
        }else {
            commodities = commodityMapperExt.getShoppingCart(req.getUserId());
        }

        if (!CollectionUtils.isEmpty(commodities)){
            commodities.forEach(commodity -> {
                commodity.setImg1("http://localhost:8080/img/seekExperts?type=4&picName="+commodity.getImg1());
            });
            PageInfo<Commodity> pageInfo = new PageInfo<>(commodities);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
    }

    /**
     * 新增商品信息
     * @param req
     * @return
     */
    @Override
    public Object insertCommodity(CommodityQueryReq req) {
        try {
            Commodity com = null;
            com.setId(CreateTestDataUtil.createSerialId());
            com.setCommodityNumber(CreateTestDataUtil.createUserName());
            com.setCommodityName(req.getCommodityName());
            com.setCommodityScript(req.getCommodityScript());
            com.setCommodityPrice(req.getCommodityPrice());
            com.setCommodityState(req.getCommodityState());
            com.setNumber(req.getNumber());
            com.setCreateBy(req.getUserId());
            commodityMapperExt.insert(com);

            StoreCommodity storeCommodity = null;
            storeCommodity.setId(CreateTestDataUtil.createSerialId());
            storeCommodity.setStoreId(req.getStoreNumber());
            storeCommodity.setCommodityId(com.getId());
            storeCommodity.setCreateBy(req.getUserId());
            storeCommodityMapperExt.insert(storeCommodity);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**
     * 删除商品信息
     * @param com
     * @return
     */
    @Override
    public Object deleteCommodity(Commodity com) {
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
    public Object updateCommodity(Commodity com) {
        try {
            commodityMapperExt.update(com);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @Override
    public Object findById(Commodity com) {
        List<Commodity> commodities = new ArrayList<>();
        try {
            commodities = commodityMapperExt.findById(com);
            if (!CollectionUtils.isEmpty(commodities)) {
                commodities.forEach(commodity -> {
                    commodity.setImg1("http://localhost:8080/img/seekExperts?type=4&picName=" + commodity.getImg1());
                    commodity.setImg2("http://localhost:8080/img/seekExperts?type=4&picName=" + commodity.getImg2());
                    commodity.setImg3("http://localhost:8080/img/seekExperts?type=4&picName=" + commodity.getImg3());
                    commodity.setImg4("http://localhost:8080/img/seekExperts?type=4&picName=" + commodity.getImg4());
                    commodity.setImg5("http://localhost:8080/img/seekExperts?type=4&picName=" + commodity.getImg5());
                });
            }
        } catch (Exception e) {
            return -1;
        }
        return commodities;
    }

    @Override
    public List<Commodity> getShoppingCart(String userId) {
        return commodityMapperExt.getShoppingCart(userId);
    }
}
