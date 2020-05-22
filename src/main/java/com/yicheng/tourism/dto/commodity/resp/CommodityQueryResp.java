package com.yicheng.tourism.dto.commodity.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommodityQueryResp {
    private String id;

    private String commodityNumber;

    private String commodityName;

    private String commodityScript;

    private Double commodityPrice;

    private String commodityState;

    private Integer number;

    private String img1;

    private String img2;

    private String img3;

    private String img4;

    private String img5;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
