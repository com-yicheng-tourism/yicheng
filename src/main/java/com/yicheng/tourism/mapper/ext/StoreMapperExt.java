package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.store.req.StoreQueryReq;
import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreMapperExt {

    /**根据条件进行查询
     * @param req
     * @return
     */
    List<Store> qryByCondition(@Param("c") StoreQueryReq req);

    /**
     * 插入数据
     * @param store
     * @return
     */
    int insertStore(@Param("c")StoreUpdateReq store);

    /**
     * 编辑数据
     * @param store
     */
    int updateStore(@Param("c") StoreUpdateReq store);

    /**
     * 删除数据
     * @param id
     */
    int deleteStore(@Param("id")String id);

    void insertBatch(@Param("list")List<Store> list);
}
