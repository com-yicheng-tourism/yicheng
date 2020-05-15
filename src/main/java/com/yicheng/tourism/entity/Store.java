package com.yicheng.tourism.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class Store {
    private String id;

    private String storeHead;

    private String storeNumber;

    private String storeName;

    private String storeScript;

    private String authorPhone;

    private String storeState;

    private String createBy;

    @Transient
    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getStoreHead() {
        return storeHead;
    }

    public void setStoreHead(String storeHead) {
        this.storeHead = storeHead == null ? null : storeHead.trim();
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber == null ? null : storeNumber.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreScript() {
        return storeScript;
    }

    public void setStoreScript(String storeScript) {
        this.storeScript = storeScript == null ? null : storeScript.trim();
    }

    public String getAuthorPhone() {
        return authorPhone;
    }

    public void setAuthorPhone(String authorPhone) {
        this.authorPhone = authorPhone == null ? null : authorPhone.trim();
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState == null ? null : storeState.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}