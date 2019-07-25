package com.lhd.qd.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString
@ApiModel(value = "基础详情视图对象")
public class BaseDetailVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    private Long updateUserId;
    @ApiModelProperty(value = "修改人姓名")
    private String updateUserName;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
}
