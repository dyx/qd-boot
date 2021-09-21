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
public interface DataRuleConverter {

    DataRuleConverter INSTANCE = Mappers.getMapper(DataRuleConverter.class);

    /**
     * 选中dto 转换为 选中树vo
     * @param saveDto
     * @return
     */
    DataRuleCheckedTreeVo checkedDto2CheckedTreeVo(DataRuleCheckedDto saveDto);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    @Mapping(target = "customDeptIds", expression = "java( cn.hutool.core.util.ArrayUtil.join(saveDto.getCustomDeptIds(),\",\") )")
    DataRuleDo saveDto2Do(DataRuleSaveDto saveDto);
}
