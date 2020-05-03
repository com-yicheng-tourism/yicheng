package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreMapperExt {

    /**根据条件进行查询
     * @param req
     * @return
     */
    List<Store> qryByCondition(@Param("c") StoreQueryReq req);
}
