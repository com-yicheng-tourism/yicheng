package com.yicheng.tourism.dto.meal.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询套餐列表列表请求参数")
@Data
public class MealQueryReq {

    @ApiModelProperty(value = "查询第几页",dataType = "Integer")
    private Integer page;
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(value = "套餐id",dataType = "String")
    private String id;
    @ApiModelProperty(value = "商品编号",dataType = "String")
    private String commodityId;

}
