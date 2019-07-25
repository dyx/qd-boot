package com.lhd.qd.module.sys.trans.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * 翻译视图对象
 * @author lhd
 */
@Setter
@Getter
@ToString
public class TransVO {

    private Map<String, Object> transValue;
    private String readField;
    private String writeField;
    private Class writeFieldType;

    public TransVO() { }

    public TransVO(String readField, String writeField, Class writeFieldType, Map<String, Object> transValue) {
        this.readField = readField;
        this.writeField = writeField;
        this.writeFieldType = writeFieldType;
        this.transValue = transValue;
    }
}
