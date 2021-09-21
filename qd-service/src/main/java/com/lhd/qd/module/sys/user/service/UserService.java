package com.lhd.qd.module.sys.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.user.model.dto.UserPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserRefPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDto;
import com.lhd.qd.module.sys.user.model.entity.UserDo;
import com.lhd.qd.module.sys.user.model.vo.UserDetailVo;
import com.lhd.qd.module.sys.user.model.vo.UserListVo;
import com.lhd.qd.module.sys.user.model.vo.UserRefListVo;

import java.util.List;

/**
 * @author lhd
 */
public interface UserService extends QdBaseService<UserDo> {

    /**
     * 用户分页列表
     * @param query
     * @return
     */
    IPage<UserListVo> pageUser(UserPageQuery query);

    /**
     * 用户参照分页列表
     * @param query
     * @return
     */
    IPage<UserRefListVo> pageRefUser(UserRefPageQuery query);

    /**
     * 用户详情
     * @param id
     * @return
     */
    UserDetailVo getUserById(Long id);

    /**
     * 新增用户
     * @param saveDto
     */
    void saveUser(UserSaveDto saveDto);

    /**
     * 修改用户
     * @param id
     * @param saveDto
     */
    void updateUser(Long id, UserSaveDto saveDto);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 批量删除用户
     * @param idList
     */
    void batchRemoveUser(List<Long> idList);
}
