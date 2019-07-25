package com.lhd.qd.module.sys.data.rule.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 数据规则新增传输对象
 * @author lhd
 * @since 2019-07-22
 */
@Setter
@Getter
@ToString
public class DataRuleDTO {

    /**
     * 权限类型列表
     */
    List<Integer> permissionTypeList;

    /**
     * 自定义部门列表
     */
    private String customDeptIds;
}