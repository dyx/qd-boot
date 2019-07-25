package com.lhd.qd.module.sys.dict.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Setter
@Getter
@ToString
@ApiModel(value = "字典新增传输对象")
public class DictSaveDTO {

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "类型")
    private String typeCode;

    @NotNull(message = "值不能为空")
    @ApiModelProperty(value = "值")
    private Integer dictValue;

    @NotBlank(message = "描述不能为空")
    @ApiModelProperty(value = "描述")
    private String dictDesc;

    @ApiModelProperty(value = "顺序")
    private Integer sortNum;
}
