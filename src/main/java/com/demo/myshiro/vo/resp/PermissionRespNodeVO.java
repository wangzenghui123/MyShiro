package com.demo.myshiro.vo.resp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(" 首页左侧菜单（用户权限）")
public class PermissionRespNodeVO {


    @ApiModelProperty("权限id")
    private String id;


    @ApiModelProperty("权限父级id")
    private String pid;

    @ApiModelProperty("权限父级名称")
    private String pName;

    @ApiModelProperty("权限名称")
    private String title;


    @ApiModelProperty("权限标识符")
    private String permission;


    @ApiModelProperty("权限资源地址")
    private String url;


    @ApiModelProperty("子权限")
    private List<PermissionRespNodeVO> children = new ArrayList<>(0);

    @ApiModelProperty("默认展开")
    private Boolean spread = true;


    @ApiModelProperty("默认展开")
    private Boolean checked = false;

}
