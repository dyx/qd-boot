package com.lhd.qd.module.sys.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author lhd
 * @since 2019-05-24
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_user_role")
public class UserRoleDo {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;

}
