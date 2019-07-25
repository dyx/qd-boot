package com.lhd.qd.module.sys.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.user.model.dto.UserPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserRefPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDTO;
import com.lhd.qd.module.sys.user.model.entity.UserDO;
import com.lhd.qd.module.sys.user.model.vo.UserDetailVO;
import com.lhd.qd.module.sys.user.model.vo.UserListVO;
import com.lhd.qd.module.sys.user.model.vo.UserRefListVO;

import java.util.List;

/**
 * @author lhd
 */
public interface UserService extends QdBaseService<UserDO> {

    /**
     * 用户分页列表
     * @param query
     * @return
     */
    IPage<UserListVO> pageUser(UserPageQuery query);

    /**
     * 用户参照分页列表
     * @param query
     * @return
     */
    IPage<UserRefListVO> pageRefUser(UserRefPageQuery query);

    /**
     * 用户详情
     * @param id
     * @return
     */
    UserDetailVO getUserById(Long id);

    /**
     * 新增用户
     * @param saveDTO
     */
    void saveUser(UserSaveDTO saveDTO);

    /**
     * 修改用户
     * @param id
     * @param saveDTO
     */
    void updateUser(Long id, UserSaveDTO saveDTO);

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
