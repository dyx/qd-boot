package com.lhd.qd.module.sys.menu.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import com.lhd.qd.tree.ITree;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_menu")
public class MenuDo extends QdBaseDo implements ITree {

    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 前端路由名称
     */
    private String pageRouterName;
    /**
     * 前端页面位置
     */
    private String pagePath;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 首页url
     */
    private String indexUrl;

    /**
     * 图标名称
     */
    private String iconName;

}
