package com.lhd.qd.module.sys.menu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Data
@ApiModel(value = "菜单新增传输对象")
public class MenuSaveDto {

    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "首页url")
    private String indexUrl;
    @ApiModelProperty(value = "前端路由名称")
    private String pageRouterName;
    @ApiModelProperty(value = "前端页面位置")
    private String pagePath;
}
