package com.lhd.qd.util;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.lhd.qd.constant.dict.DataObjEnum;
import com.lhd.qd.constant.dict.DataPermissionTypeEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleDTO;
import com.lhd.qd.module.sys.data.rule.service.impl.DataRuleServiceImpl;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.lhd.qd.constant.CommonConsts.*;
import static com.lhd.qd.constant.dict.DataPermissionTypeEnum.*;

/**
 * 数据权限工具类
 * @author lhd
 */
public class DataPermissionUtils {

    private final static String ALL_PERM = " (1=1) ";
    private final static String NO_PERM = " (1<>1) ";

    /**
     * 获取权限拼接sql
     * @param dataObjEnum
     * @return
     */
    public static String getSql(DataObjEnum dataObjEnum) {
        return getSql(dataObjEnum, DATA_PERM_OWNER_ID, DATA_PERM_OWNER_DEPT_ID);
    }

    /**
     * 获取权限拼接sql
     * @param dataObjEnum
     * @param userColumn
     * @param deptColumn
     * @param <T>
     * @return
     */
    public static <T> String getSql(DataObjEnum dataObjEnum, SFunction<T, ?> userColumn, SFunction<T, ?> deptColumn) {
        return getSql(dataObjEnum, LambdaConvertUtils.lambdaToColumn(userColumn), LambdaConvertUtils.lambdaToColumn(deptColumn));
    }

    /**
     * 获取权限拼接sql
     * @param dataObjEnum
     * @param userColumn
     * @param deptColumn
     * @return
     */
    public static String getSql(DataObjEnum dataObjEnum, String userColumn, String deptColumn) {

        UserCacheVO userCacheVO = UserUtils.getCurrentUser();
        // 超级管理员为所有权限
        if (SUPER_ADMIN_USER_ID.equals(userCacheVO.getId())) {
            return ALL_PERM;
        }

        if (dataObjEnum == null || StringUtils.isEmpty(userColumn) || StringUtils.isEmpty(deptColumn)) {
            throw new BusinessException("参数不能为空");
        }

        DataRuleDTO dataRuleDTO = SpringUtils.getBean(DataRuleServiceImpl.class)
                .getPermissionTypeList(dataObjEnum.getId(), userCacheVO.getRoleIdList());
        if (dataRuleDTO == null) {
            throw new BusinessException("为查询到相关的数据权限配置");
        }

        String sql = "";
        List<Integer> permissionTypeList = dataRuleDTO.getPermissionTypeList();
        if (permissionTypeList != null && permissionTypeList.size() > 0) {
            // 有一个无权，全部无权
            if (permissionTypeList.contains(DataPermissionTypeEnum.NO_PERMISSION.getValue())) {
                return NO_PERM;
            }

            // 有一个有权，全部有权
            if (permissionTypeList.contains(DataPermissionTypeEnum.ALL_PERMISSION.getValue())) {
                return ALL_PERM;
            }

            // 取最大权限
            Integer maxPermissionType = Collections.max(permissionTypeList);
            if (COMPANY_AND_SUB_COMPANY.getValue().equals(maxPermissionType)) {

                sql = getDeptSql(deptColumn, userCacheVO.getCompanyAndSubCompanyDeptIds());
            } else if (COMPANY.getValue().equals(maxPermissionType)) {

                sql = getDeptSql(deptColumn, userCacheVO.getCompanyDeptIds());
            } else if (DataPermissionTypeEnum.DEPT_AND_SUB_DEPT.getValue().equals(maxPermissionType)) {

                sql = getDeptSql(deptColumn, userCacheVO.getDeptAndSubDeptDeptIds());
            } else if (DEPT.getValue().equals(maxPermissionType)) {

                sql = String.format(" %s = '%s'", deptColumn, userCacheVO.getDeptId());
            } else if (OWNER.getValue().equals(maxPermissionType)) {

                sql = String.format(" %s = '%s'", userColumn, userCacheVO.getId());
            }
        }

        // 自定义部门权限
        if (StringUtils.isNotEmpty(dataRuleDTO.getCustomDeptIds())) {

            sql += String.format(" %s %s", StringUtils.isNotEmpty(sql) ? "or" : "",
                    getDeptSql(deptColumn, dataRuleDTO.getCustomDeptIds()));
        }

        return " (" + sql + ") ";
    }

    /**
     * 是否有权
     * @param dataObjEnum
     * @param ownerId
     * @param ownerDeptId
     */
    public static void hasPerm(DataObjEnum dataObjEnum, Long ownerId, Long ownerDeptId) {

        UserCacheVO userCacheVO = UserUtils.getCurrentUser();
        // 超级管理员为所有权限
        if (SUPER_ADMIN_USER_ID.equals(userCacheVO.getId())) {
            return;
        }

        if (dataObjEnum == null || ownerId == null || ownerDeptId == null) {
            throw new BusinessException("参数不能为空");
        }

        DataRuleDTO dataRuleDTO = SpringUtils.getBean(DataRuleServiceImpl.class)
                .getPermissionTypeList(dataObjEnum.getId(), userCacheVO.getRoleIdList());
        if (dataRuleDTO == null) {
            throw new BusinessException("为查询到相关的数据权限配置");
        }

        Set<Long> deptIdSet = new HashSet<>();
        List<Integer> permissionTypeList = dataRuleDTO.getPermissionTypeList();
        if (permissionTypeList != null && permissionTypeList.size() > 0) {
            // 有一个无权，全部无权
            if (permissionTypeList.contains(DataPermissionTypeEnum.NO_PERMISSION.getValue())) {
                throw new BusinessException("暂无该条记录的操作权限");
            }

            // 有一个有权，全部有权
            if (permissionTypeList.contains(DataPermissionTypeEnum.ALL_PERMISSION.getValue())) {
                return;
            }

            // 取最大权限
            Integer maxPermissionType = Collections.max(permissionTypeList);
            if (COMPANY_AND_SUB_COMPANY.getValue().equals(maxPermissionType)) {

                appendDeptId(userCacheVO.getCompanyAndSubCompanyDeptIds(), deptIdSet);
            } else if (COMPANY.getValue().equals(maxPermissionType)) {

                appendDeptId(userCacheVO.getCompanyDeptIds(), deptIdSet);
            } else if (DataPermissionTypeEnum.DEPT_AND_SUB_DEPT.getValue().equals(maxPermissionType)) {

                appendDeptId(userCacheVO.getDeptAndSubDeptDeptIds(), deptIdSet);
            } else if (DEPT.getValue().equals(maxPermissionType)) {
                deptIdSet.add(userCacheVO.getDeptId());
            } else if (OWNER.getValue().equals(maxPermissionType)) {

                if (!ownerDeptId.equals(userCacheVO.getId())) {
                    throw new BusinessException("暂无该条记录的操作权限");
                }
            }
        }

        // 自定义部门权限
        appendDeptId(dataRuleDTO.getCustomDeptIds(), deptIdSet);

        if (!deptIdSet.contains(ownerDeptId)) {
            throw new BusinessException("暂无该条记录的操作权限");
        }
    }

    private static void appendDeptId(String deptIds, Set<Long> deptIdSet) {

        if (StringUtils.isNotEmpty(deptIds)) {
            String[] customDeptIds = deptIds.split(",");
            for (String customDeptId : customDeptIds) {
                if (StringUtils.isNotEmpty(customDeptId)) {
                    deptIdSet.add(Long.valueOf(customDeptId));
                }
            }
        }
    }

    private static String getDeptSql(String column, String customDeptIds) {

        Set<String> idSet = new HashSet<>(Arrays.asList(customDeptIds.split(",")))
                .stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
        return String.format(" %s in (%s)", column, StringUtils.join(idSet, ","));
    }
}
