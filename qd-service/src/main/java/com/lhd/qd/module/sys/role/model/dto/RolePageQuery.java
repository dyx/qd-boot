package com.lhd.qd.module.sys.role.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Data
@ApiModel(value = "角色分页查询对象")
public class RolePageQuery extends QdBasePageQuery {

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
