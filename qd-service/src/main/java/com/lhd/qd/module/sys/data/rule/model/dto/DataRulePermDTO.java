package com.lhd.qd.module.sys.data.rule.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-07-24
 */
@Setter
@Getter
@ToString
public class DataRulePermDTO {

    private Integer permissionType;
    private String permissionTypeName;
}