package com.lhd.qd.module.sys.role.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.vo.ResourceTreeVo;
import com.lhd.qd.module.sys.role.model.dto.RoleCheckedResourceDto;
import com.lhd.qd.module.sys.role.model.dto.RoleResourceDto;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDto;
import com.lhd.qd.module.sys.role.model.entity.RoleDo;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedMenuTreeVo;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVo;
import com.lhd.qd.module.sys.role.model.vo.RoleListVo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-05-23
 */
@Mapper
public abstract class AbstractRoleConverter {

    public static AbstractRoleConverter INSTANCE = Mappers.getMapper(AbstractRoleConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<RoleListVo> doPage2ListVoPage(IPage<RoleDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract RoleListVo do2ListVo(RoleDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    public abstract RoleDetailVo do2DetailVo(RoleDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    public abstract RoleDo saveDto2Do(RoleSaveDto saveDto);

    /**
     * dto 转换为 树vo
     * @param dto
     * @return
     */
    @Mapping(target = "id", expression = "java( Math.abs(dto.getId()) )")
    @Mapping(target = "parentId", expression = "java( Math.abs(dto.getParentId()) )")
    public abstract ResourceTreeVo roleResourceDto2ResourceTreeVo(RoleResourceDto dto);

    /**
     * dto 转换为 树vo
     * @param dto
     * @return
     */
    public abstract RoleCheckedMenuTreeVo roleCheckedResourceDto2RoleCheckedMenuTreeVo(RoleCheckedResourceDto dto);
}
