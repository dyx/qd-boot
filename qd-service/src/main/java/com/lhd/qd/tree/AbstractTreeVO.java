package com.lhd.qd.tree;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString
public class AbstractTreeVO {

    @ApiModelProperty(value = "子菜单集合")
    protected List<AbstractTreeVO> children;
}
