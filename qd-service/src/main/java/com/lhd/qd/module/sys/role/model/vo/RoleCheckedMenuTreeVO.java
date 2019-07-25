package com.lhd.qd.module.sys.role.model.vo;

import com.lhd.qd.tree.AbstractTreeVO;
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
@ApiModel(value = "角色选中菜单视图对象")
public class RoleCheckedMenuTreeVO extends AbstractTreeVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "菜单名称")
    private String title;
    @ApiModelProperty(value = "选中状态")
    private Boolean checked;
}
