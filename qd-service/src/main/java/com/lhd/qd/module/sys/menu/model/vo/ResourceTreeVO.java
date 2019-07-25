package com.lhd.qd.module.sys.menu.model.vo;

import com.lhd.qd.tree.AbstractTreeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString
@ApiModel(value = "资源树视图对象")
public class ResourceTreeVO extends AbstractTreeVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String title;
}
