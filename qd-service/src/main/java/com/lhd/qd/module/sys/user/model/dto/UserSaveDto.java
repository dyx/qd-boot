package com.lhd.qd.module.sys.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lhd
 */
@Data
@ApiModel(value = "用户新增传输对象")
public class UserSaveDto {

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "部门id")
    private Long deptId;
}
