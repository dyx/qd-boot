package com.lhd.qd.constant.dict;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhd
 */
public enum PageElementMethodEnum implements IDict {

    /**
     * 页面元素请求方法
     */
    DELETE(4, "DELETE"),
    PUT(3, "PUT"),
    POST(2, "POST"),
    GET(1, "GET");

    private Integer value;
    private String desc;

    public static Map<Integer, String> map = new HashMap<>(4);
    static {
        map.put(1, "GET");
        map.put(2, "POST");
        map.put(3, "PUT");
        map.put(4, "DELETE");
    }

    PageElementMethodEnum(Integer value, String desc) {
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
