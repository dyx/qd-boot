package com.lhd.qd.module.sys.user.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.user.model.entity.UserRoleDo;
import com.lhd.qd.module.sys.user.model.vo.UserAssignRoleVo;
import com.lhd.qd.module.sys.user.model.vo.UserListVo;
import com.lhd.qd.module.sys.user.model.vo.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户角色 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-05-24
 */
public interface UserRoleMapper extends QdBaseMapper<UserRoleDo> {

    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<UserRoleVo> selectRoleListByUserId(Long userId);

    /**
     * 获取所有角色，并设置用户的角色为选中状态
     * @param userId
     * @return
     */
    List<UserAssignRoleVo> selectRoleListWithChecked(Long userId);

    /**
     * 根据角色获取用户
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<UserListVo> selectUserPageByRoleId(IPage<UserListVo> page, @Param(Constants.WRAPPER) Wrapper queryWrapper);

    /**
     * 获取用户所有角色，使用逗号分隔
     * @param idSet
     * @return
     */
    List<Map<String, Object>> selectRoleNamesByUserId(Set<Long> idSet);
}
