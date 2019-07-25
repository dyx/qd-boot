package com.lhd.qd.module.sys.data.rule.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.data.rule.model.dto.DataObjDTO;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRulePermDTO;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDO;

import java.util.List;

/**
 * <p>
 * 数据规则 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-07-24
 */
public interface DataRuleMapper extends QdBaseMapper<DataRuleDO> {

    List<DataObjDTO> selectDataObjList();

    List<DataRulePermDTO> selectDataPermList();
}
