package com.lhd.qd.module.sys.menu.model.vo;

import com.lhd.qd.tree.AbstractTreeVo;
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
@ApiModel(value = "前端路由树视图对象")
public class RouterTreeVo extends AbstractTreeVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "前端路由名称")
    private String pageRouterName;
    @ApiModelProperty(value = "前端页面位置")
    private String pagePath;
    @ApiModelProperty(value = "图标名称")
    private String iconName;
}
