package com.lhd.qd.module.sale.custom.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-07-19
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "客户分页查询对象")
public class CustomPageQuery extends QdBasePageQuery {

    @ApiModelProperty(value = "姓名")
    private String customName;
}
