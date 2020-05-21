package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.home.resp.HomeCountResp;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapperExt {

    /**根据用户名哈讯用户信息
     * @param userName
     * @return
     */
    User qryByUserName(@Param("c") String userName);

    /**根据邮箱查询用户信息
     * @param mail
     * @return
     */
    User qryByMail(@Param("c") String mail);

    /**根据条件进行查询
     * @param req
     * @return
     */
    List<User> qryByCondition(@Param("c") UserQryConditionReq req);

    /**根虎用户名更新
     * @param user
     * @return
     */
    int updateByUsername(@Param("c") User user);

    /**
     * 批量插入
     * @param users
     */
    void insertBatch(@Param("list")List<User> users);

    int verification(String username,String apiUrl);

    @Select("select serial_id from t_user where user_name=#{username}")
    String qrySerialId(String username);

    HomeCountResp homeCount();

    @Select("select * from t_user where user_name=#{c.userName}")
    List<User> findById(@Param("c") UpdateUserInfoReq req);

    @Select("select user_pwd from t_user where user_name = #{userName}")
    String findByUserName(@Param("userName") String userName);
}
