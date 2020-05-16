package com.yicheng.tourism.entity;

import java.util.Date;

public class Order {
    private String id;

    private String storeId;

    private String commodityName;

    private String commodityId;

    private String userId;

    private Double planPrice;

    private String ifDiscount;

    private Double actrualPrice;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public String getIfDiscount() {
        return ifDiscount;
    }

    public void setIfDiscount(String ifDiscount) {
        this.ifDiscount = ifDiscount == null ? null : ifDiscount.trim();
    }

    public Double getActrualPrice() {
        return actrualPrice;
    }

    public void setActrualPrice(Double actrualPrice) {
        this.actrualPrice = actrualPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}