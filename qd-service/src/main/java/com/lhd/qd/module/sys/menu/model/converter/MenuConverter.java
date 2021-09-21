package com.lhd.qd.module.sys.menu.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.MenuDo;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVo;
import com.lhd.qd.module.sys.menu.model.vo.MenuListVo;
import com.lhd.qd.module.sys.menu.model.vo.ResourceTreeVo;
import com.lhd.qd.module.sys.menu.model.vo.RouterTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Mapper
public interface MenuConverter {

    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     *
     * @param doPage
     * @return
     */
    Page<MenuListVo> doPage2ListVoPage(IPage<MenuDo> doPage);

    /**
     * do 转换为 列表vo
     *
     * @param dataObj
     * @return
     */
    MenuListVo do2ListVo(MenuDo dataObj);

    /**
     * do 转换为 详情vo
     *
     * @param dataObj
     * @return
     */
    MenuDetailVo do2DetailVo(MenuDo dataObj);

    /**
     * do 转换为 路由树vo
     *
     * @param dataObj
     * @return
     */
    RouterTreeVo do2RouterTreeVo(MenuDo dataObj);

    /**
     * do 转换为 资源树vo
     *
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "menuName")
    ResourceTreeVo do2ResourceTreeVo(MenuDo dataObj);


    /**
     * 新增dto 转换为 do
     *
     * @param saveDto
     * @return
     */
    MenuDo saveDto2Do(MenuSaveDto saveDto);
}
