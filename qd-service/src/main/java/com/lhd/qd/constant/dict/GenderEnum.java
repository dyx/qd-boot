package com.lhd.qd.constant.dict;

/**
 * @author lhd
 */
public enum GenderEnum implements IDict {

    /**
     * 性别
     */
    FEMALE(2, "女"),
    MALE(1, "男");

    private Integer value;
    private String desc;

    GenderEnum(Integer value, String desc) {
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
