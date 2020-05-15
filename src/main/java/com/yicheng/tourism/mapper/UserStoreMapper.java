package com.yicheng.tourism.mapper;

import com.yicheng.tourism.entity.UserStore;
import com.yicheng.tourism.entity.UserStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserStoreMapper {
    long countByExample(UserStoreExample example);

    int deleteByExample(UserStoreExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserStore record);

    int insertSelective(UserStore record);

    List<UserStore> selectByExample(UserStoreExample example);

    UserStore selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserStore record, @Param("example") UserStoreExample example);

    int updateByExample(@Param("record") UserStore record, @Param("example") UserStoreExample example);

    int updateByPrimaryKeySelective(UserStore record);

    int updateByPrimaryKey(UserStore record);
}