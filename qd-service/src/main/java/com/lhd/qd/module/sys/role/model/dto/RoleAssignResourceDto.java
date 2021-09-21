package com.lhd.qd.module.sys.role.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Setter
@Getter
@ToString
@ApiModel(value = "角色分配资源传输对象")
public class RoleAssignResourceDto {

    @ApiModelProperty(value = "菜单列表")
    private List<Long> menuList;

    @ApiModelProperty(value = "页面元素列表")
    private List<Long> pageElementList;
}
