package com.lhd.qd.module.sale.custom.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDto;
import com.lhd.qd.module.sale.custom.model.entity.CustomDo;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVo;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-07-19
 */
@Mapper
public interface CustomConverter {

    CustomConverter INSTANCE = Mappers.getMapper(CustomConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    Page<CustomListVo> doPage2ListVoPage(IPage<CustomDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    CustomListVo do2ListVo(CustomDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    CustomDetailVo do2DetailVo(CustomDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    CustomDo saveDto2Do(CustomSaveDto saveDto);
}
