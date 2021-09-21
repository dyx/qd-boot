package com.lhd.qd.module.sys.org.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Data
@ApiModel(value = "组织树传输对象")
public class OrgTreeDto {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String title;
    @ApiModelProperty(value = "类型")
    private Integer type;
}
