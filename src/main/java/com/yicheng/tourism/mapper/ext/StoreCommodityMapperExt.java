package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.entity.StoreCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreCommodityMapperExt {

    void insertBatch(@Param("list")List<StoreCommodity> list);

    void insert(@Param("c") StoreCommodity storeCommodity);
}
