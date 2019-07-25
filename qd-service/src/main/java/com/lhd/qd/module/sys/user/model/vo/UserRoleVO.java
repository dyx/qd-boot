package com.lhd.qd.module.sys.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString
@ApiModel(value = "用户角色视图对象")
public class UserRoleVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;
}
