package com.lhd.qd.module.sys.menu.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDTO;
import com.lhd.qd.module.sys.menu.model.entity.MenuDO;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVO;
import com.lhd.qd.module.sys.menu.model.vo.MenuListVO;
import com.lhd.qd.module.sys.menu.model.vo.ResourceTreeVO;
import com.lhd.qd.module.sys.menu.model.vo.RouterTreeVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Mapper
public abstract class AbstractMenuConverter {

    public static AbstractMenuConverter INSTANCE = Mappers.getMapper(AbstractMenuConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<MenuListVO> doPage2ListVOPage(IPage<MenuDO> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract MenuListVO do2ListVO(MenuDO dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    public abstract MenuDetailVO do2DetailVO(MenuDO dataObj);

    /**
     * do 转换为 路由树vo
     * @param dataObj
     * @return
     */
    public abstract RouterTreeVO do2RouterTreeVO(MenuDO dataObj);

    /**
     * do 转换为 资源树vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "menuName")
    public abstract ResourceTreeVO do2ResourceTreeVO(MenuDO dataObj);


    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract MenuDO saveDTO2DO(MenuSaveDTO saveDTO);
}
