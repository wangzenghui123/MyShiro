package com.demo.myshiro.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("首页信息")
public class HomeRespVO {

    @ApiModelProperty("首页用户信息")
    private UserInfoRespVO userInfoRespVO;


    @ApiModelProperty("首页左侧菜单（用户权限）")
    private PermissionRespNodeVO permissionRespNodeVO;
}
