package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.entity.Nav;
import com.yicheng.tourism.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NavMapperExt {


    List<Nav> qryByRole(@Param("c") List<String> roleId);

    List<Nav> qryAll();
}