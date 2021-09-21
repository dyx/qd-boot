package com.lhd.qd.module.sys.dict.model.vo;

import com.lhd.qd.base.BaseDetailVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-06-01
 */
@Setter
@Getter
@ToString
@ApiModel(value = "字典类型详情视图对象")
public class DictTypeDetailVo extends BaseDetailVo {

    @ApiModelProperty(value = "编码")
    private String typeCode;
    @ApiModelProperty(value = "描述")
    private String typeDesc;
    @ApiModelProperty(value = "备注")
    private String remark;
}
