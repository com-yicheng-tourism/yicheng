package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.commodity.req.CommodityQueryReq;
import com.yicheng.tourism.entity.commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapperExt {

    List<commodity> qryByCondition(@Param("c")CommodityQueryReq req);

    void insert(@Param("c")commodity com);

    void update(@Param("c")commodity com);

    void delete(@Param("id")String id);

    void insertBatch(@Param("list")List<commodity> list);
}
