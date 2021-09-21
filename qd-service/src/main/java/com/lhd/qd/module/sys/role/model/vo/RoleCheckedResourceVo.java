package com.lhd.qd.module.sys.role.model.vo;

import com.lhd.qd.tree.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Data
@ApiModel(value = "角色选中资源对象视图对象")
public class RoleCheckedResourceVo {

    @ApiModelProperty(value = "菜单列表")
    private List<AbstractTreeVo> menuList;

    @ApiModelProperty(value = "页面元素映射")
    private Map<Long, List<RoleCheckedPageElementVo>> pageElementMap;
}
