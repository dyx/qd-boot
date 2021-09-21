package com.lhd.qd.module.sys.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 */
@Data
@ApiModel(value = "用户角色视图对象")
public class UserAssignRoleVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "选中状态")
    private Boolean checked;
}
