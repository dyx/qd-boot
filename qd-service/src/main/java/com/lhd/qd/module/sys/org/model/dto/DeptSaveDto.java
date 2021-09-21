package com.lhd.qd.module.sys.org.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Data
@ApiModel(value = "部门新增传输对象")
public class DeptSaveDto {

    @ApiModelProperty(value = "所属公司")
    private Long companyId;
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String deptName;
}
