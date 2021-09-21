package com.lhd.qd.module.sys.data.rule.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 数据规则新增传输对象
 * @author lhd
 * @since 2019-07-22
 */
@Data
public class DataRuleDto {

    /**
     * 权限类型列表
     */
    List<Integer> permissionTypeList;

    /**
     * 自定义部门列表
     */
    private String customDeptIds;
}
