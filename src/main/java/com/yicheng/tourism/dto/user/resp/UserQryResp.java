package com.yicheng.tourism.dto.user.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "查询用户列表返回")
@Data
public class UserQryResp {
    private String userName;

    private String mail;

    private String nickName;

    private String userPwd;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    private String profilePic;

    private String userPhone;

    private String userAddress;

    private String type;

    private String ipAddress;

    private Date createTime;

    private String createId;

    private Date modifyTime;

    private String modifyId;

    private Boolean isLogout;

}
