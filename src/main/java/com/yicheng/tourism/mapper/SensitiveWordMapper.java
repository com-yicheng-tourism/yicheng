package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.SensitiveWord;
import com.yicheng.tourism.entity.SensitiveWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SensitiveWordMapper {
    long countByExample(SensitiveWordExample example);

    int deleteByExample(SensitiveWordExample example);

    int deleteByPrimaryKey(String id);

    int insert(SensitiveWord record);

    int insertSelective(SensitiveWord record);

    List<SensitiveWord> selectByExample(SensitiveWordExample example);

    SensitiveWord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SensitiveWord record, @Param("example") SensitiveWordExample example);

    int updateByExample(@Param("record") SensitiveWord record, @Param("example") SensitiveWordExample example);

    int updateByPrimaryKeySelective(SensitiveWord record);

    int updateByPrimaryKey(SensitiveWord record);
}