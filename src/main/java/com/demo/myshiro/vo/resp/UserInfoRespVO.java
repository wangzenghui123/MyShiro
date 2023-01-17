package com.demo.myshiro.vo.resp;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("首页用户信息")
public class UserInfoRespVO {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("部门id")
    private String deptId;

    @ApiModelProperty("部门名称")
    private String deptName;
}
