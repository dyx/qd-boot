package com.lhd.qd.constant.dict;

/**
 * @author lhd
 */
public enum DataObjEnum {

    /**
     * 数据对象
     */
    CUSTOM(2, "客户"),
    USER(1, "用户");

    private Integer id;
    private String name;

    DataObjEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
