package com.demo.myshiro.vo.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("角色分页请求")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePageReqVO {

    @ApiModelProperty("当前页页码")
    private int pageNum;

    @ApiModelProperty("每页数据数量")
    private int pageSize;
}
