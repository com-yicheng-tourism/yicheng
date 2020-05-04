package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.entity.User;
import org.apache.ibatis.annotations.Param;

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
}
