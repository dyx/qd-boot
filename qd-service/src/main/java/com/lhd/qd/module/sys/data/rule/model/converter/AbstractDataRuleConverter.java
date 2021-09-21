package com.lhd.qd.module.sys.data.rule.model.converter;

import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleCheckedDto;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDto;
import com.lhd.qd.module.sys.data.rule.model.entity.DataRuleDo;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVo;
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
     * @param saveDto
     * @return
     */
    public abstract DataRuleCheckedTreeVo checkedDto2CheckedTreeVo(DataRuleCheckedDto saveDto);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    @Mapping(target = "customDeptIds", expression = "java( org.apache.commons.lang3.StringUtils.join(saveDto.getCustomDeptIds(),\",\") )")
    public abstract DataRuleDo saveDto2Do(DataRuleSaveDto saveDto);
}
