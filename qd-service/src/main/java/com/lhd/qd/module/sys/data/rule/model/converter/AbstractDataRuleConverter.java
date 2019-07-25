package com.lhd.qd.module.sys.data.rule.model.converter;

import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleCheckedDTO;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDTO;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDO;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-07-24
 */
@Mapper
public abstract class AbstractDataRuleConverter {

    public static AbstractDataRuleConverter INSTANCE = Mappers.getMapper(AbstractDataRuleConverter.class);

    /**
     * 选中dto 转换为 选中树vo
     * @param saveDTO
     * @return
     */
    public abstract DataRuleCheckedTreeVO checkedDTO2CheckedTreeVO(DataRuleCheckedDTO saveDTO);

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    @Mapping(target = "customDeptIds", expression = "java( org.apache.commons.lang3.StringUtils.join(saveDTO.getCustomDeptIds(),\",\") )")
    public abstract DataRuleDO saveDTO2DO(DataRuleSaveDTO saveDTO);
}
