package com.lhd.qd.module.sys.data.rule.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-07-24
 */
@Data
@ApiModel(value = "数据规则树传输对象")
public class DataRuleCheckedDto {

    @ApiModelProperty(value = "数据对象id")
    private Integer dataObjId;
    @ApiModelProperty(value = "数据对象名称")
    private String dataObjName;
    @ApiModelProperty(value = "权限类型")
    private Integer permissionType;
    @ApiModelProperty(value = "权限类型名称")
    private String permissionTypeName;
    @ApiModelProperty(value = "自定义部门id")
    private String customDeptIds;
    @ApiModelProperty(value = "选中状态")
    private Boolean checked;
}
