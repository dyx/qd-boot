package com.lhd.qd.module.sys.org.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Setter
@Getter
@ToString
@ApiModel(value = "部门列表视图对象")
public class DeptListVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "所属公司")
    private Long companyId;
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String deptName;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
