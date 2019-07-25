package com.lhd.qd.module.sys.org.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_dept")
public class DeptDO extends QdBaseDO {

    /**
     * 所属公司
     */
    private Long companyId;
    /**
     * 上级id
     */
    private Long parentId;
    /**
     * 名称
     */
    private String deptName;

}
