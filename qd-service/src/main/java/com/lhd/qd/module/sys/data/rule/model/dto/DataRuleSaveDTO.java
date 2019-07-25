package com.lhd.qd.module.sys.data.rule.model.dto;

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
@ApiModel(value = "数据规则新增传输对象")
public class DataRuleSaveDTO {

    @ApiModelProperty(value = "数据对象id")
    private Integer dataObjId;
    @ApiModelProperty(value = "权限类型")
    private Integer permissionType;
    @ApiModelProperty(value = "自定义部门id")
    private List<Integer> customDeptIds;
}
