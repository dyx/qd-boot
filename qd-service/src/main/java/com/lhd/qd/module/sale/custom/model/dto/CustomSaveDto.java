package com.lhd.qd.module.sale.custom.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-07-19
 */
@Data
@ApiModel(value = "客户新增传输对象")
public class CustomSaveDto {

    @ApiModelProperty(value = "姓名")
    private String customName;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "所属人id")
    private Long ownerId;
    @ApiModelProperty(value = "所属部门id")
    private Long ownerDeptId;
}
