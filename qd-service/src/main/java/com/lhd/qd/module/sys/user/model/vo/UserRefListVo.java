package com.lhd.qd.module.sys.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 */
@Data
@ApiModel(value = "用户参照列表视图对象")
public class UserRefListVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
