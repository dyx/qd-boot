package com.lhd.qd.module.sys.dict.model.vo;

import com.lhd.qd.base.BaseDetailVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Setter
@Getter
@ToString
@ApiModel(value = "字典详情视图对象")
public class DictDetailVo extends BaseDetailVo {

    @ApiModelProperty(value = "类型编码")
    private String typeCode;
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "值")
    private Integer dictValue;
    @ApiModelProperty(value = "描述")
    private String dictDesc;
    @ApiModelProperty(value = "顺序")
    private Integer sortNum;
}
