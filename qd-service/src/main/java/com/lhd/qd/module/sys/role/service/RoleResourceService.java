package com.lhd.qd.module.sys.role.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.module.sys.role.model.dto.RoleAssignResourceDto;
import com.lhd.qd.module.sys.role.model.entity.RoleResourceDo;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedResourceVo;
import com.lhd.qd.module.sys.role.model.vo.RoleResourceVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色权限 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
public interface RoleResourceService extends QdBaseService<RoleResourceDo> {

    /**
     * 根据角色id列表获取菜单树
     * @param roleIdList
     * @return
     */
    List<AbstractTreeVo> getMenuTreeByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色获取页面元素
     * @param roleIdList
     * @return
     */
    Map<Long, String[]>  getPageElementMapByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色获取权限列表
     * @param roleIdList
     * @return
     */
    List<String> getPermissionListByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色获取资源
     * @param roleId
     * @return
     */
    RoleResourceVo getResourceByRoleId(Long roleId);

    /**
     * 获取角色下的资源，并设置选中状态
     * @param roleId
     * @return
     */
    RoleCheckedResourceVo getCheckedResourceByRoleId(Long roleId);

    /**
     * 分配角色资源
     * @param roleId
     * @param dto
     */
    void updateRoleResource(Long roleId, RoleAssignResourceDto dto);
}
