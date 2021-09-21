package com.lhd.qd.module.sys.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户数据对象
 * @author lhd
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_user")
public class UserDo extends QdBaseDo {

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 删除标识
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;
}
