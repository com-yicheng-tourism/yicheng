package com.yicheng.tourism.dto.user.resp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yicheng.tourism.entity.User;
import lombok.Data;

@Data
public class UserExcel {

    @ExcelProperty(value = "用户名",index = 0)
    private String userName;
    @ExcelProperty(value = "昵称",index = 1)
    private String nickName;
    @ExcelProperty(value = "邮箱",index = 2)
    private String mail;
    @ExcelProperty(value = "电话",index = 3)
    private String phone;

    public UserExcel() {
    }
    public UserExcel(User user) {
        this.userName=user.getUserName();
        this.nickName=user.getNickName();
        this.mail=user.getMail();
        this.phone=user.getUserPhone();
    }
}
