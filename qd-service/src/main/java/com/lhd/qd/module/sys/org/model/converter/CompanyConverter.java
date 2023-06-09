package com.lhd.qd.module.sys.org.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDto;
import com.lhd.qd.module.sys.org.model.entity.CompanyDo;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVo;
import com.lhd.qd.module.sys.org.model.vo.CompanyListVo;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Mapper
public interface CompanyConverter {

    CompanyConverter INSTANCE = Mappers.getMapper(CompanyConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    Page<CompanyListVo> doPage2ListVoPage(IPage<CompanyDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    CompanyListVo do2ListVo(CompanyDo dataObj);

    /**
     * do 转换为 树vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "companyName")
    CompanyTreeVo do2TreeVo(CompanyDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    CompanyDetailVo do2DetailVo(CompanyDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    CompanyDo saveDto2Do(CompanySaveDto saveDto);
}
