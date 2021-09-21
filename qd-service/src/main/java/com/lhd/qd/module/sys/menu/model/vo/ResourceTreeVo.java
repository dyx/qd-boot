package com.lhd.qd.module.sys.menu.model.vo;

import com.lhd.qd.tree.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Data
@ApiModel(value = "资源树视图对象")
public class ResourceTreeVo extends AbstractTreeVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String title;
}
