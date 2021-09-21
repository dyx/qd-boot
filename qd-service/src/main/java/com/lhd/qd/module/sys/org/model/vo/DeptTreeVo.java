package com.lhd.qd.module.sys.org.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Data
@ApiModel(value = "部门树视图对象")
public class DeptTreeVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "所属公司")
    private Long companyId;
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String title;
    @ApiModelProperty(value = "子集合")
    private List<DeptTreeVo> children;
}
