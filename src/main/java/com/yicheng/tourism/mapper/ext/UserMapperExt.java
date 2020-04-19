package com.yicheng.tourism.mapper.ext;

import com.yicheng.tourism.entity.User;
import org.apache.ibatis.annotations.Param;

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
}
