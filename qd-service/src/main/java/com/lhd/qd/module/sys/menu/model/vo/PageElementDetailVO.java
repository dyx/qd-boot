package com.lhd.qd.module.sys.menu.model.vo;

import com.lhd.qd.base.BaseDetailVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString
@ApiModel(value = "页面元素详情视图对象")
public class PageElementDetailVO extends BaseDetailVO {

    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
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
