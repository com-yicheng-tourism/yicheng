package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapperExt {

    List<Commodity> qryByCondition(@Param("c")CommodityQueryReq req);

    void insert(@Param("c")Commodity com);

    void update(@Param("c")Commodity com);

    void delete(@Param("com")Commodity com);

    void insertBatch(@Param("list")List<Commodity> list);

    List<Commodity> findById(@Param("com")Commodity com);

    List<Commodity> getShoppingCart(@Param("userId") String userId);
}
