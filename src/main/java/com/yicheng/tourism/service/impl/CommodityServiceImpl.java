package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.*;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.CommodityMapper;
import com.yicheng.tourism.mapper.ShoppingCartMapper;
import com.yicheng.tourism.mapper.ext.CommodityMapperExt;
import com.yicheng.tourism.mapper.ext.StoreCommodityMapperExt;
import com.yicheng.tourism.mapper.ext.UserStoreMapperExt;
import com.yicheng.tourism.service.CommodityService;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.util.UUIDUtil;
import com.yicheng.tourism.util.CreateTestDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapperExt commodityMapperExt;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private StoreCommodityMapperExt storeCommodityMapperExt;
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private UserStoreMapperExt userStoreMapperExt;

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
            Commodity com = new Commodity();
            com.setId(CreateTestDataUtil.createSerialId());
            com.setCommodityNumber(CreateTestDataUtil.createUserName());
            com.setCommodityName(req.getCommodityName());
            com.setCommodityScript(req.getCommodityScript());
            com.setCommodityPrice(req.getCommodityPrice());
            com.setCommodityState(req.getCommodityState());
            com.setNumber(req.getNumber());
            com.setCreateBy(req.getUserId());
            commodityMapperExt.insert(com);

            UserStore userStore = userStoreMapperExt.findById(req.getUserId());

            StoreCommodity storeCommodity = new StoreCommodity();
            storeCommodity.setId(CreateTestDataUtil.createSerialId());
            storeCommodity.setStoreId(userStore.getStoreId());
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
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

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

    @Override
    public BaseResponse<String> addToShoppingCart(CommodityQueryReq req, HttpServletRequest request) {
//        BaseResponse<User> verification = userService.verification(request);
//        User verificationData = verification.getData();
//        if (StringUtils.isEmpty(req.getId())){
//            return new BaseResponse<>(RespStatusEnum.SERIAL_CODE_IS_NULL.getCode(),RespStatusEnum.SERIAL_CODE_IS_NULL.getMessage());
//        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setSerialCode(UUIDUtil.get());
        shoppingCart.setUserId(req.getUserId());
        shoppingCart.setCommodityId(req.getId());
        shoppingCart.setCreateTime(new Date());
        shoppingCart.setCreateId(req.getUserId());
        int i = shoppingCartMapper.insertSelective(shoppingCart);
        if ( i != 0 ){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"添加成功");
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"添加失败,请联系管理员处理!");
    }

    @Override
    public BaseResponse<String> changeState(String id, HttpServletRequest request) {

//        BaseResponse<User> verification = userService.verification(request);
//        User user = verification.getData();
        CommodityExample commodityExample = new CommodityExample();
        CommodityExample.Criteria criteria = commodityExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Commodity> commodities = commodityMapper.selectByExample(commodityExample);
        String status = null ;
        if (!StringUtils.isEmpty(commodities.get(0))){
            status = commodities.get(0).getCommodityState().equals("0")  ? "1" : "0";
        }
        Commodity commodity = new Commodity();
        commodity.setId(id);
        commodity.setCommodityState(status);
        commodity.setUpdateTime(new Date());

        int i = commodityMapper.updateByPrimaryKeySelective(commodity);
        if (i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"状态切换成功");
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"状态切换失败");
    }
}
