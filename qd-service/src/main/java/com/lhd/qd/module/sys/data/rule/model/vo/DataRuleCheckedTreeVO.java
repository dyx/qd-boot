package com.lhd.qd.module.sys.data.rule.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lhd
 * @since 2019-07-24
 */
@Setter
@Getter
@ToString
@ApiModel(value = "数据规则树视图对象")
public class DataRuleCheckedTreeVO {

    @ApiModelProperty(value = "权限类型")
    private Integer id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "层级")
    private Integer level;
    @ApiModelProperty(value = "数据对象id")
    private Integer dataObjId;
    @ApiModelProperty(value = "自定义部门id")
    private String customDeptIds;
    @ApiModelProperty(value = "选中状态")
    private Boolean checked;

    @ApiModelProperty(value = "子集合")
    private List<DataRuleCheckedTreeVO> children;
}
