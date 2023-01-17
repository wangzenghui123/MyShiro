package com.demo.myshiro.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登录相应VO")
public class LoginRespVO {

    @ApiModelProperty(value = "业务token")
    private String accessToken;

    @ApiModelProperty(value = "业务刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "用户id")
    private String userId;

}
