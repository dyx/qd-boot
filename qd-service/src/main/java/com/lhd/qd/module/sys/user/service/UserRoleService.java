package com.lhd.qd.module.sys.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBasePageQuery;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.user.model.entity.UserRoleDo;
import com.lhd.qd.module.sys.user.model.vo.UserAssignRoleVo;
import com.lhd.qd.module.sys.user.model.vo.UserListVo;
import com.lhd.qd.module.sys.user.model.vo.UserRoleVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户角色 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-24
 */
public interface UserRoleService extends QdBaseService<UserRoleDo> {

    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    List<UserRoleVo> getRoleListByUserId(Long userId);

    /**
     * 获取所有角色，并设置用户的角色为选中状态
     * @param userId
     * @return
     */
    List<UserAssignRoleVo> getRoleListWithChecked(Long userId);

    /**
     * 用户分配角色
     * @param userId
     * @param roleIdList
     */
    void updateUserAssignRole(Long userId, List<Long> roleIdList);

    /**
     * 角色关联用户
     * @param roleId
     * @param userIdList
     */
    void updateRoleAssociateUser(Long roleId, List<Long> userIdList);

    /**
     * 根据角色获取用户列表
     * @param roleId
     * @param query
     * @return
     */
    IPage<UserListVo> getUserPageByRoleId(Long roleId, QdBasePageQuery query);

    /**
     * 获取用户所有角色，使用逗号分隔
     * @param idSet
     * @return
     */
    List<Map<String, Object>> getRoleNamesByUserId(Set<Long> idSet);
}
