package com.lhd.qd.module.sys.role.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author lhd
 * @since 2019-05-23
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_role")
public class RoleDO extends QdBaseDO {

    /**
     * 角色名称
     */
    private String roleName;

}
