package com.lhd.qd.module.sys.data.rule.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.data.rule.model.dto.DataObjDto;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRulePermDto;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDo;

import java.util.List;

/**
 * <p>
 * 数据规则 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-07-24
 */
public interface DataRuleMapper extends QdBaseMapper<DataRuleDo> {

    /**
     * 查询数据对象列表
     * @return
     */
    List<DataObjDto> selectDataObjList();

    /**
     * 查询数据权限列表
     * @return
     */
    List<DataRulePermDto> selectDataPermList();
}
