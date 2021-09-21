package com.lhd.qd.module.sys.role.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Setter
@Getter
@ToString
@ApiModel(value = "角色选中页面元素视图对象")
public class RoleCheckedPageElementVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "菜单")
    private Long menuId;
    @ApiModelProperty(value = "元素编码")
    private String elementCode;
    @ApiModelProperty(value = "元素名称")
    private String elementName;
    @ApiModelProperty(value = "选中状态")
    private Boolean checked;
}
