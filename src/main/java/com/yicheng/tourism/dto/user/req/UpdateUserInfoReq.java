package com.yicheng.tourism.dto.user.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class UpdateUserInfoReq {
    private String serialId;

    private String userName;

    private String mail;

    private String nickName;

    private String userPwd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    private String role;

    private String type;

    private String profilePic;

    private String userPhone;

    private String userAddress;

    private Boolean isLogout;
}
