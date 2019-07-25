package com.lhd.qd.module.sys.data.rule.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.dict.DataPermissionTypeEnum;
import com.lhd.qd.module.sys.data.rule.dao.DataRuleMapper;
import com.lhd.qd.module.sys.data.rule.model.converter.AbstractDataRuleConverter;
import com.lhd.qd.module.sys.data.rule.model.dto.DataObjDTO;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleDTO;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRulePermDTO;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDTO;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDO;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVO;
import com.lhd.qd.module.sys.data.rule.service.DataRuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据规则 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-24
 */
@Service
public class DataRuleServiceImpl extends QdBaseServiceImpl<DataRuleMapper, DataRuleDO> implements DataRuleService {

    private static final Integer BASE_PERM_TYPE = 1;
    private static final Integer CUSTOM_PERM_TYPE = 2;

    @Override
    public DataRuleDTO getPermissionTypeList(Integer objId, List<Long> roleIdList) {

        List<DataRuleDO> doList = list(Wrappers.<DataRuleDO>lambdaQuery()
                .eq(DataRuleDO::getDataObjId, objId)
                .in(DataRuleDO::getRoleId, roleIdList));

        if (doList == null || doList.size() == 0) {
            return null;
        }

        List<Integer> permissionTypeList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (DataRuleDO dataObj : doList) {
            if (DataPermissionTypeEnum.CUSTOM_PERMISSION.getValue().equals(dataObj.getPermissionType())) {
                if (StringUtils.isNotEmpty(dataObj.getCustomDeptIds())) {
                    // 拼接自定义部门id
                    builder.append(",");
                    builder.append(dataObj.getCustomDeptIds());
                }
            } else {
                permissionTypeList.add(dataObj.getPermissionType());
            }
        }

        DataRuleDTO dataRuleDTO = new DataRuleDTO();
        dataRuleDTO.setPermissionTypeList(permissionTypeList);
        dataRuleDTO.setCustomDeptIds(builder.toString());

        return dataRuleDTO;
    }

    @Override
    public List<DataRuleCheckedTreeVO> getCheckedTreeByRoleId(Long roleId) {

        // 已配置的数据规则
        List<DataRuleDO> doList = list(Wrappers.<DataRuleDO>lambdaQuery().eq(DataRuleDO::getRoleId, roleId));
        Map<String, DataRuleDO> ruleMap = new HashMap<>(16);
        for (DataRuleDO dataObj: doList) {
            ruleMap.put(getRuleKey(dataObj.getDataObjId(), dataObj.getPermissionType()), dataObj);
        }

        // 数据对象
        List<DataObjDTO> objList = baseMapper.selectDataObjList();

        // 数据权限类型
        List<DataRulePermDTO> permList = baseMapper.selectDataPermList();

        // 构建树结构
        List<DataRuleCheckedTreeVO> treeList =  new ArrayList<>();
        for (DataObjDTO obj : objList) {

            // 第一级 数据对象
            DataRuleCheckedTreeVO objTreeVO = new DataRuleCheckedTreeVO();
            objTreeVO.setId(obj.getId());
            objTreeVO.setTitle(obj.getObjName());
            objTreeVO.setLevel(1);

            List<DataRuleCheckedTreeVO> objChildren = new ArrayList<>();
            // 第二级 权限类别
            DataRuleCheckedTreeVO baseCategoryTreeVO = new DataRuleCheckedTreeVO();
            baseCategoryTreeVO.setId(BASE_PERM_TYPE);
            baseCategoryTreeVO.setTitle("基础权限");
            baseCategoryTreeVO.setLevel(2);
            // 第三级 权限类型
            baseCategoryTreeVO.setChildren(getPermList(false, obj.getId(), permList, ruleMap));
            objChildren.add(baseCategoryTreeVO);


            DataRuleCheckedTreeVO customCategoryTreeVO = new DataRuleCheckedTreeVO();
            customCategoryTreeVO.setId(CUSTOM_PERM_TYPE);
            customCategoryTreeVO.setTitle("自定义权限");
            customCategoryTreeVO.setLevel(2);
            customCategoryTreeVO.setChildren(getPermList(true, obj.getId(), permList, ruleMap));
            objChildren.add(customCategoryTreeVO);


            objTreeVO.setChildren(objChildren);
            treeList.add(objTreeVO);
        }

        return treeList;
    }

    @Override
    public void batchSaveByRoleId(Long roleId, List<DataRuleSaveDTO> dtoList) {

        if (roleId == null) {
            return;
        }

        removeByRoleId(roleId);

        if (dtoList == null || dtoList.size() == 0) {
            return;
        }

        List<DataRuleDO> doList = new ArrayList<>();
        for (DataRuleSaveDTO dto: dtoList) {

            DataRuleDO dataObj = AbstractDataRuleConverter.INSTANCE.saveDTO2DO(dto);
            dataObj.setRoleId(roleId);
            doList.add(dataObj);
        }

        saveBatch(doList);
    }


    private String getRuleKey(Integer objId, Integer permissionType) {
        return String.format("%s,%s", objId, permissionType);
    }

    private List<DataRuleCheckedTreeVO> getPermList(Boolean isCustomType, Integer objId, List<DataRulePermDTO> permList, Map<String, DataRuleDO> ruleMap) {

        List<DataRuleCheckedTreeVO> list = new ArrayList<>();
        for (DataRulePermDTO perm : permList) {

            if (isCustomType) {
                if (perm.getPermissionType() == 3) {

                    list.add(convertTreeVO(objId, perm, ruleMap));
                }
            } else {
                if (perm.getPermissionType() != 3) {

                    list.add(convertTreeVO(objId, perm, ruleMap));
                }
            }
        }
        return list;
    }

    private DataRuleCheckedTreeVO convertTreeVO(Integer objId, DataRulePermDTO perm, Map<String, DataRuleDO> ruleMap) {

        DataRuleCheckedTreeVO permTreeVO = new DataRuleCheckedTreeVO();
        permTreeVO.setId(perm.getPermissionType());
        permTreeVO.setTitle(perm.getPermissionTypeName());
        permTreeVO.setDataObjId(objId);
        permTreeVO.setLevel(3);

        DataRuleDO dataRuleDO = ruleMap.get(getRuleKey(objId, perm.getPermissionType()));
        if (dataRuleDO != null) {
            permTreeVO.setChecked(true);
            permTreeVO.setCustomDeptIds(dataRuleDO.getCustomDeptIds());
        } else {
            permTreeVO.setChecked(false);
        }
        return permTreeVO;
    }

    private void removeByRoleId(Long roleId) {

        remove(Wrappers.<DataRuleDO>lambdaQuery().eq(DataRuleDO::getRoleId, roleId));
    }
}
