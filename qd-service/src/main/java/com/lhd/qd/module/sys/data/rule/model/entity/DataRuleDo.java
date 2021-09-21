package com.lhd.qd.module.sys.data.rule.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 数据规则
 * </p>
 *
 * @author lhd
 * @since 2019-07-24
 */
@Data
@TableName("sys_data_rule")
public class DataRuleDo {

    /**
     * 数据对象id
     */
    private Integer dataObjId;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限类型
     */
    private Integer permissionType;
    /**
     * 自定义部门id
     */
    private String customDeptIds;

}
