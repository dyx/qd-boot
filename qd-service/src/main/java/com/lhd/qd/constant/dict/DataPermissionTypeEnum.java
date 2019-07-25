package com.lhd.qd.constant.dict;

/**
 * @author lhd
 */
public enum DataPermissionTypeEnum implements IDict {

    /**
     * 数据权限类型
     */
    COMPANY_AND_SUB_COMPANY(500, "本公司及子级公司"),
    COMPANY(400, "本公司"),
    DEPT_AND_SUB_DEPT(300, "本部门及子级部门"),
    DEPT(200, "本部门"),
    OWNER(100, "本人"),
    CUSTOM_PERMISSION(3, "自定义权限"),
    ALL_PERMISSION(2, "所有权限"),
    NO_PERMISSION(1, "无权");

    private Integer value;
    private String desc;

    DataPermissionTypeEnum(Integer value, String desc) {
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
