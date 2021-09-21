package com.lhd.qd.module.sys.role.model.vo;

import com.lhd.qd.base.BaseDetailVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Setter
@Getter
@ToString
@ApiModel(value = "角色详情视图对象")
public class RoleDetailVo extends BaseDetailVo {

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
