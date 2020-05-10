package com.yicheng.tourism.dto.role.resp;

import com.yicheng.tourism.entity.Nav;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.Role;
import lombok.Data;

@Data
public class RolePermissionResp {

    private Integer id;

    private Integer pid;

    private String name;

    private String remark;


    public RolePermissionResp() {
    }

    public RolePermissionResp(Role role) {
        this.id = Integer.parseInt(role.getId());
        this.name = role.getName();
        this.pid = Integer.parseInt("0");
        this.remark = role.getDescription();
    }
    public RolePermissionResp(Nav nav) {
        this.id = Integer.parseInt(nav.getId());
        this.name = nav.getName();
        this.pid = Integer.parseInt(nav.getPid());
        this.remark = nav.getDescription();
    }
    public RolePermissionResp(Permission permission) {
        this.id = Integer.parseInt(permission.getId());
        this.name = permission.getName();
        this.pid = Integer.parseInt(permission.getPid());
        this.remark = permission.getDescription();
    }

}
