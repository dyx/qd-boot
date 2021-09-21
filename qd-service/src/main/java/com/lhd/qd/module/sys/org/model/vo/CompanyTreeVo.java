package com.lhd.qd.module.sys.org.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Setter
@Getter
@ToString
@ApiModel(value = "公司树视图对象")
public class CompanyTreeVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "子集合")
    private List<CompanyTreeVo> children;
}
