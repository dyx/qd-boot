package com.lhd.qd.module.sys.role.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.menu.model.entity.MenuDO;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDO;
import com.lhd.qd.module.sys.role.model.dto.RoleCheckedResourceDTO;
import com.lhd.qd.module.sys.role.model.dto.RoleResourceDTO;
import com.lhd.qd.module.sys.role.model.entity.RoleResourceDO;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedPageElementVO;
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
public interface RoleResourceMapper extends QdBaseMapper<RoleResourceDO> {

    /**
     * 根据角色获取菜单
     * @param roleIdList
     * @return
     */
    List<MenuDO> selectMenuListByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色获取分组的页面元素
     * @param roleIdList
     * @return
     */
    List<PageElementDO> selectPageElementGroupByRoleIdList(List<Long> roleIdList);

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
    List<RoleResourceDTO> selectPageElementAndMenuListByRoleId(Long roleId);

    /**
     * 根据角色获取选中菜单
     * @param roleId
     * @return
     */
    List<RoleCheckedResourceDTO> selectCheckedMenuByRoleId(Long roleId);

    /**
     * 根据角色获取选中页面元素
     * @param roleId
     * @return
     */
    List<RoleCheckedPageElementVO> selectCheckedPageElementByRoleId(@Param("roleId") Long roleId);
}
