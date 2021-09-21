package com.lhd.qd.module.sys.menu.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Mapper
public interface PageElementConverter {

    PageElementConverter INSTANCE = Mappers.getMapper(PageElementConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    Page<PageElementListVo> doPage2ListVoPage(IPage<PageElementDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    PageElementListVo do2ListVo(PageElementDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    PageElementDetailVo do2DetailVo(PageElementDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    PageElementDo saveDto2Do(PageElementSaveDto saveDto);
}
