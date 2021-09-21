package com.lhd.qd.module.sys.dict.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Data
@ApiModel(value = "字典列表视图对象")
public class DictListVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "类型")
    private String typeCode;
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "值")
    private Integer dictValue;
    @ApiModelProperty(value = "描述")
    private String dictDesc;
    @ApiModelProperty(value = "顺序")
    private Integer sortNum;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
