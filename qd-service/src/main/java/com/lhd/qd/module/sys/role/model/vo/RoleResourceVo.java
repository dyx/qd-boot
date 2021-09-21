package com.lhd.qd.module.sys.role.model.vo;

import com.lhd.qd.tree.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lhd
 */
@Data
@ApiModel(value = "角色资源对象视图对象")
public class RoleResourceVo {

    @ApiModelProperty(value = "菜单列表")
    private List<AbstractTreeVo> menuList;

    @ApiModelProperty(value = "页面元素列表")
    private List<AbstractTreeVo> pageElementList;
}
