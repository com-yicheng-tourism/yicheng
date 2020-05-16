package com.yicheng.tourism.dto.store.req;

import lombok.Data;

import java.util.Date;

@Data
public class StoreUpdateReq {
    private String id;

    private String userStoreId;

    private String userId;

    private String storeNumber;

    private String storeName;

    private String storeScript;

    private String authorPhone;

    private String storeState;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
