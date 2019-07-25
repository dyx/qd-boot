package com.lhd.qd.module.sys.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBasePageQuery;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.module.sys.user.dao.UserRoleMapper;
import com.lhd.qd.module.sys.user.model.entity.UserRoleDO;
import com.lhd.qd.module.sys.user.model.vo.UserAssignRoleVO;
import com.lhd.qd.module.sys.user.model.vo.UserListVO;
import com.lhd.qd.module.sys.user.model.vo.UserRoleVO;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 用户角色 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-24
 */
@Service
public class UserRoleServiceImpl extends QdBaseServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleService {

    @Override
    public List<UserRoleVO> getRoleListByUserId(Long userId) {

        return baseMapper.selectRoleListByUserId(userId);
    }

    @Override
    public List<UserAssignRoleVO> getRoleListWithChecked(Long userId) {
        return baseMapper.selectRoleListWithChecked(userId);
    }

    @Override
    public void updateUserAssignRole(Long userId, List<Long> roleIdList) {

        if (userId == null) {
            return;
        }

        remove(Wrappers.<UserRoleDO>lambdaQuery().eq(UserRoleDO::getUserId, userId));

        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }

        saveBatch(getUserRole(userId, roleIdList));
    }

    @Override
    public void updateRoleAssociateUser(Long roleId, List<Long> userIdList) {
        if (roleId == null) {
            return;
        }

        remove(Wrappers.<UserRoleDO>lambdaQuery().eq(UserRoleDO::getRoleId, roleId));

        if (userIdList == null || userIdList.size() == 0) {
            return;
        }

        saveBatch(getUserRole(roleId, userIdList));
    }

    @Override
    public IPage<UserListVO> getUserPageByRoleId(Long roleId, QdBasePageQuery query) {

        IPage<UserListVO> page = new Page<>(query.getPage(), query.getSize());

        return baseMapper.selectUserPageByRoleId(page,
                Wrappers.query().eq("ur.role_id", roleId));
    }

    @Override
    public List<Map<String, Object>> getRoleNamesByUserId(Set<Long> idSet) {

        if (idSet == null || idSet.size() == 0) {
            return null;
        }

        return baseMapper.selectRoleNamesByUserId(idSet);
    }

    private List<UserRoleDO> getUserRole(Long id, List<Long> idList) {
        return idList.stream().map(item -> {
                    UserRoleDO dataObj = new UserRoleDO();
                    dataObj.setUserId(id);
                    dataObj.setRoleId(item);
                    return dataObj;
                }).collect(toList());
    }

}
