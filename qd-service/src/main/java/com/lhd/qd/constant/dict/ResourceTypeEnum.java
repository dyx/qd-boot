package com.lhd.qd.constant.dict;

/**
 * @author lhd
 */
public enum ResourceTypeEnum implements IDict {

    /**
     * 资源类型
     */
    PAGE_ELEMENT(2, "页面元素"),
    MENU(1, "菜单");

    private Integer value;
    private String desc;

    ResourceTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
