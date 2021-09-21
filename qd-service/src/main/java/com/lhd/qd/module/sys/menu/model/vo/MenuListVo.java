package com.lhd.qd.module.sys.menu.model.vo;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@ApiModel(value = "菜单列表视图对象")
public class MenuListVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "前端路由名称")
    private String pageRouterName;
    @ApiModelProperty(value = "前端页面位置")
    private String pagePath;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
