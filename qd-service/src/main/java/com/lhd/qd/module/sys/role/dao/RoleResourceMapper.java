package com.lhd.qd.module.sys.role.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.menu.model.entity.MenuDo;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDo;
import com.lhd.qd.module.sys.role.model.dto.RoleCheckedResourceDto;
import com.lhd.qd.module.sys.role.model.dto.RoleResourceDto;
import com.lhd.qd.module.sys.role.model.entity.RoleResourceDo;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedPageElementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
public interface RoleResourceMapper extends QdBaseMapper<RoleResourceDo> {

    /**
     * 根据角色获取菜单
     * @param roleIdList
     * @return
     */
    List<MenuDo> selectMenuListByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色获取分组的页面元素
     * @param roleIdList
     * @return
     */
    List<PageElementDo> selectPageElementGroupByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色获取权限
     * @param separator
     * @param roleIdList
     * @return
     */
    List<String> selectPermissionListByRoleIdList(@Param("separator") String separator,
                                                  @Param("roleIdList") List<Long> roleIdList);

    /**
     * 根据角色获取元素和元素所属菜单列表
     * @param roleId
     * @return
     */
    List<RoleResourceDto> selectPageElementAndMenuListByRoleId(Long roleId);

    /**
     * 根据角色获取选中菜单
     * @param roleId
     * @return
     */
    List<RoleCheckedResourceDto> selectCheckedMenuByRoleId(Long roleId);

    /**
     * 根据角色获取选中页面元素
     * @param roleId
     * @return
     */
    List<RoleCheckedPageElementVo> selectCheckedPageElementByRoleId(@Param("roleId") Long roleId);
}
