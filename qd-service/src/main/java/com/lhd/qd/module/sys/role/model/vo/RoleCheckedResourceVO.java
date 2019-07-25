package com.lhd.qd.module.sys.role.model.vo;

import com.lhd.qd.tree.AbstractTreeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString
@ApiModel(value = "角色选中资源对象视图对象")
public class RoleCheckedResourceVO {

    @ApiModelProperty(value = "菜单列表")
    private List<AbstractTreeVO> menuList;

    @ApiModelProperty(value = "页面元素映射")
    private Map<Long, List<RoleCheckedPageElementVO>> pageElementMap;
}
