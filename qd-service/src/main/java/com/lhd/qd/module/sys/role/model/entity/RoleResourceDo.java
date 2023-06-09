package com.lhd.qd.module.sys.role.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@Data
@TableName("sys_role_resource")
public class RoleResourceDo {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 资源类型
     */
    private Integer resourceType;
    /**
     * 资源id
     */
    private Long resourceId;

}
