package com.lhd.qd.module.sys.role.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.role.dao.RoleMapper;
import com.lhd.qd.module.sys.role.model.converter.AbstractRoleConverter;
import com.lhd.qd.module.sys.role.model.dto.RolePageQuery;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDTO;
import com.lhd.qd.module.sys.role.model.entity.RoleDO;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVO;
import com.lhd.qd.module.sys.role.model.vo.RoleListVO;
import com.lhd.qd.module.sys.role.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-23
 */
@Service
public class RoleServiceImpl extends QdBaseServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Override
    public IPage<RoleListVO> pageRole(RolePageQuery query) {

        IPage<RoleDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<RoleDO>lambdaQuery()
                        .like(StringUtils.isNotEmpty(query.getRoleName()), RoleDO::getRoleName, query.getRoleName()));

        return AbstractRoleConverter.INSTANCE.doPage2ListVOPage(doPage);
    }

    @Override
    public RoleDetailVO getRoleById(Long id) {

        RoleDO dataObj = getById(id);

        return AbstractRoleConverter.INSTANCE.do2DetailVO(dataObj);
    }

    @Override
    public void saveRole(RoleSaveDTO saveDTO) {

        valid(null, saveDTO);

        RoleDO dataObj = AbstractRoleConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);
    }

    @Override
    public void updateRole(Long id, RoleSaveDTO saveDTO) {

        valid(id, saveDTO);

        RoleDO dataObj = AbstractRoleConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeRole(Long id) {

        RoleDO dataObj = new RoleDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    public void valid(Long id, RoleSaveDTO saveDTO) {
        Integer count = count(Wrappers.<RoleDO>lambdaQuery()
                .eq(RoleDO::getRoleName, saveDTO.getRoleName())
                .ne(id != null, RoleDO::getId, id));
        if (count > 0) {
            throw new BusinessException("角色名称已存在，请更换。");
        }
    }
}
