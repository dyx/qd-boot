package com.lhd.qd.module.sys.role.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.menu.model.vo.ResourceTreeVO;
import com.lhd.qd.module.sys.role.model.dto.RoleCheckedResourceDTO;
import com.lhd.qd.module.sys.role.model.dto.RoleResourceDTO;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDTO;
import com.lhd.qd.module.sys.role.model.entity.RoleDO;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedMenuTreeVO;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVO;
import com.lhd.qd.module.sys.role.model.vo.RoleListVO;
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
    public abstract IPage<RoleListVO> doPage2ListVOPage(IPage<RoleDO> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract RoleListVO do2ListVO(RoleDO dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    public abstract RoleDetailVO do2DetailVO(RoleDO dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract RoleDO saveDTO2DO(RoleSaveDTO saveDTO);

    /**
     * dto 转换为 树vo
     * @param dto
     * @return
     */
    @Mapping(target = "id", expression = "java( Math.abs(dto.getId()) )")
    @Mapping(target = "parentId", expression = "java( Math.abs(dto.getParentId()) )")
    public abstract ResourceTreeVO roleResourceDTO2ResourceTreeVO(RoleResourceDTO dto);

    /**
     * dto 转换为 树vo
     * @param dto
     * @return
     */
    public abstract RoleCheckedMenuTreeVO roleCheckedResourceDTO2RoleCheckedMenuTreeVO(RoleCheckedResourceDTO dto);
}
