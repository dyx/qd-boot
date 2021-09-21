package com.lhd.qd.tree;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lhd
 */
@Data
public class AbstractTreeVo {

    @ApiModelProperty(value = "子菜单集合")
    protected List<AbstractTreeVo> children;
}
