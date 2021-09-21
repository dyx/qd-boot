package com.lhd.qd.module.sys.org.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Data;
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
@Data
@TableName("sys_dept")
public class DeptDo extends QdBaseDo {

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
