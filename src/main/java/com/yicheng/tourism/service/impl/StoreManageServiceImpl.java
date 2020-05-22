package com.yicheng.tourism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.Store;
import com.yicheng.tourism.entity.StoreExample;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import com.yicheng.tourism.mapper.StoreMapper;
import com.yicheng.tourism.mapper.ext.StoreMapperExt;
import com.yicheng.tourism.mapper.ext.UserStoreMapperExt;
import com.yicheng.tourism.service.StoreManageService;
import com.yicheng.tourism.service.UserService;
import com.yicheng.tourism.util.CreateTestDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class StoreManageServiceImpl implements StoreManageService {

    @Autowired
    private StoreMapperExt storeMapperExt;

    @Autowired
    private UserStoreMapperExt userStoreMapperExt;

    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private UserService userService;

    @Override
    public BaseResponse<PageInfo<Store>> findPage(StoreQueryReq req) {
        if (StringUtils.isEmpty(req.getPage())){
            req.setPage(1);
        }
        if (StringUtils.isEmpty(req.getRows())){
            req.setRows(10);
        }
        PageHelper.startPage(req.getPage(),req.getRows());
        List<Store> storeList = storeMapperExt.qryByCondition(req);
        if (!CollectionUtils.isEmpty(storeList)){
            storeList.forEach(store -> {
                store.setStoreHead("http://localhost:8080/img/seekExperts?type=2&picName="+store.getStoreHead());
            });
            PageInfo<Store> pageInfo = new PageInfo<>(storeList);
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),pageInfo);
        }
        return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage());
    }

    @Override
    public int insertStore(StoreUpdateReq store) {
        store.setId(CreateTestDataUtil.createSerialId());
        store.setStoreNumber(CreateTestDataUtil.createUserName());
        store.setCreateBy(CreateTestDataUtil.createUserName());
//        store.setUserStoreId(CreateTestDataUtil.createSerialId());
        try {
            storeMapperExt.insertStore(store);
            userStoreMapperExt.insert(store);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public int updateStore(StoreUpdateReq store) {
        try {
            storeMapperExt.updateStore(store);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @Override
    public int deleteStore(String id) {
        try {
            storeMapperExt.deleteStore(id);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    /**
     * 关闭店铺
     *
     * @return
     */
    @Override
    public BaseResponse<String> closeStore(String id, HttpServletRequest request) {
        BaseResponse<User> verification = userService.verification(request);
        User user = verification.getData();
        StoreExample storeExample = new StoreExample();
        StoreExample.Criteria criteria = storeExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Store> stores = storeMapper.selectByExample(storeExample);
        String status = null ;
        if (!StringUtils.isEmpty(stores.get(0))){
            status = stores.get(0).getStoreState().equals("0")  ? "1" : "0";
        }
        Store store = new Store();
        store.setId(id);
        store.setStoreState(status);
        store.setUpdateTime(new Date());
        store.setUpdateBy(user.getUserName());
        int i = storeMapper.updateByPrimaryKeySelective(store);
        if (i != 0){
            return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"关闭成功");
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),"关闭失败");
    }

    @Override
    public BaseResponse<Store> getStore(String userId) {
        Store store = storeMapperExt.getStore(userId);
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),store);
    }
}
