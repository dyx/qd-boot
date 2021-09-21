package com.lhd.qd.module.sys.data.rule.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.dict.DataPermissionTypeEnum;
import com.lhd.qd.module.sys.data.rule.dao.DataRuleMapper;
import com.lhd.qd.module.sys.data.rule.model.converter.AbstractDataRuleConverter;
import com.lhd.qd.module.sys.data.rule.model.dto.DataObjDto;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleDto;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRulePermDto;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDto;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDo;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVo;
import com.lhd.qd.module.sys.data.rule.service.DataRuleService;
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
public class DataRuleServiceImpl extends QdBaseServiceImpl<DataRuleMapper, DataRuleDo> implements DataRuleService {

    private static final Integer BASE_PERM_TYPE = 1;
    private static final Integer CUSTOM_PERM_TYPE = 2;

    @Override
    public DataRuleDto getPermissionTypeList(Integer objId, List<Long> roleIdList) {

        List<DataRuleDo> doList = list(Wrappers.<DataRuleDo>lambdaQuery()
                .eq(DataRuleDo::getDataObjId, objId)
                .in(DataRuleDo::getRoleId, roleIdList));

        if (doList == null || doList.size() == 0) {
            return null;
        }

        List<Integer> permissionTypeList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (DataRuleDo dataObj : doList) {
            if (DataPermissionTypeEnum.CUSTOM_PERMISSION.getValue().equals(dataObj.getPermissionType())) {
                if (StrUtil.isNotEmpty(dataObj.getCustomDeptIds())) {
                    // 拼接自定义部门id
                    builder.append(",");
                    builder.append(dataObj.getCustomDeptIds());
                }
            } else {
                permissionTypeList.add(dataObj.getPermissionType());
            }
        }

        DataRuleDto dataRuleDto = new DataRuleDto();
        dataRuleDto.setPermissionTypeList(permissionTypeList);
        dataRuleDto.setCustomDeptIds(builder.toString());

        return dataRuleDto;
    }

    @Override
    public List<DataRuleCheckedTreeVo> getCheckedTreeByRoleId(Long roleId) {

        // 已配置的数据规则
        List<DataRuleDo> doList = list(Wrappers.<DataRuleDo>lambdaQuery().eq(DataRuleDo::getRoleId, roleId));
        Map<String, DataRuleDo> ruleMap = new HashMap<>(16);
        for (DataRuleDo dataObj: doList) {
            ruleMap.put(getRuleKey(dataObj.getDataObjId(), dataObj.getPermissionType()), dataObj);
        }

        // 数据对象
        List<DataObjDto> objList = baseMapper.selectDataObjList();

        // 数据权限类型
        List<DataRulePermDto> permList = baseMapper.selectDataPermList();

        // 构建树结构
        List<DataRuleCheckedTreeVo> treeList =  new ArrayList<>();
        for (DataObjDto obj : objList) {

            // 第一级 数据对象
            DataRuleCheckedTreeVo objTreeVo = new DataRuleCheckedTreeVo();
            objTreeVo.setId(obj.getId());
            objTreeVo.setTitle(obj.getObjName());
            objTreeVo.setLevel(1);

            List<DataRuleCheckedTreeVo> objChildren = new ArrayList<>();
            // 第二级 权限类别
            DataRuleCheckedTreeVo baseCategoryTreeVo = new DataRuleCheckedTreeVo();
            baseCategoryTreeVo.setId(BASE_PERM_TYPE);
            baseCategoryTreeVo.setTitle("基础权限");
            baseCategoryTreeVo.setLevel(2);
            // 第三级 权限类型
            baseCategoryTreeVo.setChildren(getPermList(false, obj.getId(), permList, ruleMap));
            objChildren.add(baseCategoryTreeVo);


            DataRuleCheckedTreeVo customCategoryTreeVo = new DataRuleCheckedTreeVo();
            customCategoryTreeVo.setId(CUSTOM_PERM_TYPE);
            customCategoryTreeVo.setTitle("自定义权限");
            customCategoryTreeVo.setLevel(2);
            customCategoryTreeVo.setChildren(getPermList(true, obj.getId(), permList, ruleMap));
            objChildren.add(customCategoryTreeVo);


            objTreeVo.setChildren(objChildren);
            treeList.add(objTreeVo);
        }

        return treeList;
    }

    @Override
    public void batchSaveByRoleId(Long roleId, List<DataRuleSaveDto> dtoList) {

        if (roleId == null) {
            return;
        }

        removeByRoleId(roleId);

        if (dtoList == null || dtoList.size() == 0) {
            return;
        }

        List<DataRuleDo> doList = new ArrayList<>();
        for (DataRuleSaveDto dto: dtoList) {

            DataRuleDo dataObj = AbstractDataRuleConverter.INSTANCE.saveDto2Do(dto);
            dataObj.setRoleId(roleId);
            doList.add(dataObj);
        }

        saveBatch(doList);
    }


    private String getRuleKey(Integer objId, Integer permissionType) {
        return String.format("%s,%s", objId, permissionType);
    }

    private List<DataRuleCheckedTreeVo> getPermList(Boolean isCustomType, Integer objId, List<DataRulePermDto> permList, Map<String, DataRuleDo> ruleMap) {

        List<DataRuleCheckedTreeVo> list = new ArrayList<>();
        for (DataRulePermDto perm : permList) {

            if (isCustomType) {
                if (perm.getPermissionType() == 3) {

                    list.add(convertTreeVo(objId, perm, ruleMap));
                }
            } else {
                if (perm.getPermissionType() != 3) {

                    list.add(convertTreeVo(objId, perm, ruleMap));
                }
            }
        }
        return list;
    }

    private DataRuleCheckedTreeVo convertTreeVo(Integer objId, DataRulePermDto perm, Map<String, DataRuleDo> ruleMap) {

        DataRuleCheckedTreeVo permTreeVo = new DataRuleCheckedTreeVo();
        permTreeVo.setId(perm.getPermissionType());
        permTreeVo.setTitle(perm.getPermissionTypeName());
        permTreeVo.setDataObjId(objId);
        permTreeVo.setLevel(3);

        DataRuleDo dataRuleDo = ruleMap.get(getRuleKey(objId, perm.getPermissionType()));
        if (dataRuleDo != null) {
            permTreeVo.setChecked(true);
            permTreeVo.setCustomDeptIds(dataRuleDo.getCustomDeptIds());
        } else {
            permTreeVo.setChecked(false);
        }
        return permTreeVo;
    }

    private void removeByRoleId(Long roleId) {

        remove(Wrappers.<DataRuleDo>lambdaQuery().eq(DataRuleDo::getRoleId, roleId));
    }
}
