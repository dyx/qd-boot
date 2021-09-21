package com.lhd.qd.module.sys.menu.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.MenuDo;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVo;
import com.lhd.qd.module.sys.menu.model.vo.MenuListVo;
import com.lhd.qd.module.sys.menu.model.vo.ResourceTreeVo;
import com.lhd.qd.module.sys.menu.model.vo.RouterTreeVo;
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
    public abstract IPage<MenuListVo> doPage2ListVoPage(IPage<MenuDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract MenuListVo do2ListVo(MenuDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    public abstract MenuDetailVo do2DetailVo(MenuDo dataObj);

    /**
     * do 转换为 路由树vo
     * @param dataObj
     * @return
     */
    public abstract RouterTreeVo do2RouterTreeVo(MenuDo dataObj);

    /**
     * do 转换为 资源树vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "menuName")
    public abstract ResourceTreeVo do2ResourceTreeVo(MenuDo dataObj);


    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    public abstract MenuDo saveDto2Do(MenuSaveDto saveDto);
}
