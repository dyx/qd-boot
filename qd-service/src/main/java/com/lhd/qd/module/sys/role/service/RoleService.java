package com.lhd.qd.module.sys.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.role.model.dto.RolePageQuery;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDto;
import com.lhd.qd.module.sys.role.model.entity.RoleDo;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVo;
import com.lhd.qd.module.sys.role.model.vo.RoleListVo;

/**
 * <p>
 * 角色 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-23
 */
public interface RoleService extends QdBaseService<RoleDo> {

    /**
     * 角色分页列表
     * @param query
     * @return
     */
    IPage<RoleListVo> pageRole(RolePageQuery query);

    /**
     * 角色详情
     * @param id
     * @return
     */
    RoleDetailVo getRoleById(Long id);

    /**
     * 新增角色
     * @param saveDto
     */
    void saveRole(RoleSaveDto saveDto);

    /**
     * 修改角色
     * @param id
     * @param saveDto
     */
    void updateRole(Long id, RoleSaveDto saveDto);

    /**
     * 删除角色
     * @param id
     */
    void removeRole(Long id);
}
