package com.lhd.qd.module.sys.data.rule.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleDto;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDto;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDo;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVo;

import java.util.List;

/**
 * <p>
 * 数据规则 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-24
 */
public interface DataRuleService extends QdBaseService<DataRuleDo> {

    /**
     * 获取权限类型列表
     * @param objId
     * @param roleIdList
     * @return
     */
    DataRuleDto getPermissionTypeList(Integer objId, List<Long> roleIdList);

    /**
     * 根据角色获取选中树
     * @param roleId
     * @return
     */
    List<DataRuleCheckedTreeVo> getCheckedTreeByRoleId(Long roleId);

    /**
     * 根据角色批量保存
     * @param roleId
     * @param dtoList
     */
    void batchSaveByRoleId(Long roleId, List<DataRuleSaveDto> dtoList);
}
