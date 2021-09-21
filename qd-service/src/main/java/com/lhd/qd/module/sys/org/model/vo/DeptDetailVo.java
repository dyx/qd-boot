package com.lhd.qd.module.sys.org.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Data
@ApiModel(value = "部门详情视图对象")
public class DeptDetailVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "所属公司")
    private Long companyId;
    @ApiModelProperty(value = "所属公司名称")
    private String companyName;
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "上级名称")
    private String parentName;
    @ApiModelProperty(value = "名称")
    private String deptName;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "修改人id")
    private Long updateUserId;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
}
