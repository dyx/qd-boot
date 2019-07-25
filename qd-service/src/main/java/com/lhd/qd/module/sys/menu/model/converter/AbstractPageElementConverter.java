package com.lhd.qd.module.sys.menu.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDTO;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDO;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVO;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Mapper
public abstract class AbstractPageElementConverter {

    public static AbstractPageElementConverter INSTANCE = Mappers.getMapper(AbstractPageElementConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<PageElementListVO> doPage2ListVOPage(IPage<PageElementDO> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract PageElementListVO do2ListVO(PageElementDO dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    public abstract PageElementDetailVO do2DetailVO(PageElementDO dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract PageElementDO saveDTO2DO(PageElementSaveDTO saveDTO);
}
