package com.lhd.qd.module.sale.custom.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-07-19
 */
@Data
@ApiModel(value = "客户列表视图对象")
public class CustomListVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String customName;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "所属人id")
    private Long ownerId;
    @ApiModelProperty(value = "所属人姓名")
    private String ownerName;
    @ApiModelProperty(value = "所属部门id")
    private Long ownerDeptId;
    @ApiModelProperty(value = "所属部门名称")
    private String ownerDeptName;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
