package com.lhd.qd.module.sys.menu.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString
@ApiModel(value = "页面元素列表视图对象")
public class PageElementListVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
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
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
