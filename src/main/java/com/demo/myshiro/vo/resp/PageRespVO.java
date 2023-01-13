package com.demo.myshiro.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页数据返回")
public class PageRespVO <T>{

    @ApiModelProperty(value = "总记录数")
    private Long totalRows;

    @ApiModelProperty(value = "总页数")
    private Integer totalPages;

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;

    @ApiModelProperty(value = "当前页记录数")
    private Integer curPageSize;

    @ApiModelProperty(value = "数据列表")
    private List<T>list;


}
