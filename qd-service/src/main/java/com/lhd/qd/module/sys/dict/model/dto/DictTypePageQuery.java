package com.lhd.qd.module.sys.dict.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-06-01
 */
@Data
@ApiModel(value = "字典类型分页查询对象")
public class DictTypePageQuery extends QdBasePageQuery {

    @ApiModelProperty(value = "编码")
    private String typeCode;
    @ApiModelProperty(value = "描述")
    private String typeDesc;
}
