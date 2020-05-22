package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.store.req.StoreUpdateReq;
import com.yicheng.tourism.entity.UserStore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserStoreMapperExt {

    void insertBatch(@Param("list")List<UserStore> list);

    void insert(@Param("c")StoreUpdateReq store);
    @Select("select * from t_user_store where user_id = #{c}")
    UserStore findById(@Param("c")String userId);
}
