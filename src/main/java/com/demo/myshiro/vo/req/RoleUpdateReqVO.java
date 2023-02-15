package com.demo.myshiro.vo.req;


import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "更新角色请求")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateReqVO {

    @ApiModelProperty(value = "角色ID")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色权限")
    private PermissionRespNodeVO[] permissions;


}
