package com.lhd.qd.module.sys.menu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Data
@ApiModel(value = "页面元素新增传输对象")
public class PageElementSaveDto {

    @ApiModelProperty(value = "元素名称")
    private String elementName;
    @ApiModelProperty(value = "元素编码")
    private String elementCode;
    @ApiModelProperty(value = "元素类型")
    private Integer elementType;
    @ApiModelProperty(value = "请求url")
    private String url;
    @ApiModelProperty(value = "请求方法")
    private Integer method;
}
