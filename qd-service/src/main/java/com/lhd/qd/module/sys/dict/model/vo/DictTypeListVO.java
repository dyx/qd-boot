package com.lhd.qd.module.sys.dict.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-06-01
 */
@Setter
@Getter
@ToString
@ApiModel(value = "字典类型列表视图对象")
public class DictTypeListVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "编码")
    private String typeCode;
    @ApiModelProperty(value = "描述")
    private String typeDesc;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
