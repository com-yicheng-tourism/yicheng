package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapperExt {

    List<commodity> qryByCondition(@Param("c")CommodityQueryReq req);

    void insert(@Param("c")commodity com);

    void update(@Param("c")commodity com);

    void delete(@Param("com")commodity com);

    void insertBatch(@Param("list")List<commodity> list);

    List<commodity> findById(@Param("com")commodity com);
}
