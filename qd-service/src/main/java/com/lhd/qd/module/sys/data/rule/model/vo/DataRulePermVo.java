package com.lhd.qd.module.sys.data.rule.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-07-24
 */
@Setter
@Getter
@ToString
@ApiModel(value = "数据规则权限视图对象")
public class DataRulePermVo {

    @ApiModelProperty(value = "权限类型")
    private Integer permissionType;
    @ApiModelProperty(value = "自定义部门id")
    private String customDeptIds;

}
