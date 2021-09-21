package com.lhd.qd.module.sys.user.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * redis存储的用户信息
 * @author lhd
 */
@Setter
@Getter
@ToString
public class UserCacheVo {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 权限列表
     */
    private List<String> permissionList;

    /**
     * 角色列表
     */
    private List<Long> roleIdList;

    /**
     * 公司及子公司下的部门id
     */
    private String companyAndSubCompanyDeptIds;

    /**
     * 公司下的部门id
     */
    private String companyDeptIds;

    /**
     * 部门及子部门id
     */
    private String deptAndSubDeptDeptIds;


    /**
     * 与字段名对应
     */
    public final static String ID = "id";
    public final static String USERNAME = "username";
    public final static String SALT = "salt";
    public final static String PERMISSION_LIST = "permissionList";
    public final static String ROLE_ID_LIST = "roleIdList";
    public final static String COMPANY_ID = "companyId";
    public final static String DEPT_ID = "deptId";
    public final static String COMPANY_AND_SUB_COMPANY_DEPT_IDS = "companyAndSubCompanyDeptIds";
    public final static String COMPANY_DEPT_IDS = "companyDeptIds";
    public final static String DEPT_AND_SUB_DEPT_DEPT_IDS = "deptAndSubDeptDeptIds";
}
