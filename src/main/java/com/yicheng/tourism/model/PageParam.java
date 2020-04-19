package com.yicheng.tourism.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@ApiModel(description = "分页参数")
@Data
public class PageParam implements Serializable {

    @ApiModelProperty(value = "查询第几页，默认第1页", dataType = "int")
    @Min(value = 1, message = "页码不能小于1")
    @NotNull
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页多少条，默认10条", dataType = "int")
    @Min(value = 1, message = "每页数量不少于1条")
    @NotNull
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
