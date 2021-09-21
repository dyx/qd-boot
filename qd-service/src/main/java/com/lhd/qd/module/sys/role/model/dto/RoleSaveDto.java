package com.lhd.qd.module.sys.role.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Data
@ApiModel(value = "角色新增传输对象")
public class RoleSaveDto {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
