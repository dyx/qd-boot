package com.lhd.qd.module.sys.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.role.model.dto.RolePageQuery;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDTO;
import com.lhd.qd.module.sys.role.model.entity.RoleDO;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVO;
import com.lhd.qd.module.sys.role.model.vo.RoleListVO;

/**
 * <p>
 * 角色 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-23
 */
public interface RoleService extends QdBaseService<RoleDO> {

    /**
     * 角色分页列表
     * @param query
     * @return
     */
    IPage<RoleListVO> pageRole(RolePageQuery query);

    /**
     * 角色详情
     * @param id
     * @return
     */
    RoleDetailVO getRoleById(Long id);

    /**
     * 新增角色
     * @param saveDTO
     */
    void saveRole(RoleSaveDTO saveDTO);

    /**
     * 修改角色
     * @param id
     * @param saveDTO
     */
    void updateRole(Long id, RoleSaveDTO saveDTO);

    /**
     * 删除角色
     * @param id
     */
    void removeRole(Long id);
}
