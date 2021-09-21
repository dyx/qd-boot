package com.lhd.qd.module.sys.org.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Setter
@Getter
@ToString
@ApiModel(value = "公司新增传输对象")
public class CompanySaveDto {

    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String companyName;
}
