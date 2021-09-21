package com.lhd.qd.module.sys.data.rule.model.dto;

import lombok.Data;

/**
 * 权限类型
 * @author lhd
 * @since 2019-07-24
 */
@Data
public class DataRulePermTypeDto {

    private Integer type;
    private String typeName;

    public DataRulePermTypeDto (Integer type, String typeName) {

    }
}
