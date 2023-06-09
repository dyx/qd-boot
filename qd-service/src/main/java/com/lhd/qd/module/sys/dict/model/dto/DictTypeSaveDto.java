package com.lhd.qd.module.sys.dict.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-06-01
 */
@Data
@ApiModel(value = "字典类型新增传输对象")
public class DictTypeSaveDto {

    @ApiModelProperty(value = "编码")
    private String typeCode;
    @ApiModelProperty(value = "描述")
    private String typeDesc;
    @ApiModelProperty(value = "备注")
    private String remark;
}
