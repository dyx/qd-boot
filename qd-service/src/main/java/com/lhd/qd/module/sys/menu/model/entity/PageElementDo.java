package com.lhd.qd.module.sys.menu.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 页面元素
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_page_element")
public class PageElementDo extends QdBaseDo {

    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 元素名称
     */
    private String elementName;
    /**
     * 元素类型
     */
    private Integer elementType;
    /**
     * 元素编码
     */
    private String elementCode;
    /**
     * 请求url
     */
    private String url;
    /**
     * 请求方法
     */
    private Integer method;

}
