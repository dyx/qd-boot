package com.lhd.qd.module.sys.role.model.vo;

import com.lhd.qd.tree.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Data
@ApiModel(value = "角色选中菜单视图对象")
public class RoleCheckedMenuTreeVo extends AbstractTreeVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "菜单名称")
    private String title;
    @ApiModelProperty(value = "选中状态")
    private Boolean checked;
}
