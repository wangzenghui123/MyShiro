package com.demo.myshiro.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户分页请求数据")
public class UserPageReqVO {

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数据个数")
    private Integer pageSize = 10;
}
