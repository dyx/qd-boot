package com.lhd.qd.module.sys.data.rule.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限类型
 * @author lhd
 * @since 2019-07-24
 */
@Setter
@Getter
@ToString
public class DataRulePermTypeDto {

    private Integer type;
    private String typeName;

    public DataRulePermTypeDto (Integer type, String typeName) {

    }
}
