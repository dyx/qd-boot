package com.lhd.qd.module.sys.role.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Setter
@Getter
@ToString
@ApiModel(value = "角色列表视图对象")
public class RoleListVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
