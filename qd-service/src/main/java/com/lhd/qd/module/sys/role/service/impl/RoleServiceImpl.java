package com.lhd.qd.module.sys.role.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.role.dao.RoleMapper;
import com.lhd.qd.module.sys.role.model.converter.AbstractRoleConverter;
import com.lhd.qd.module.sys.role.model.dto.RolePageQuery;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDto;
import com.lhd.qd.module.sys.role.model.entity.RoleDo;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVo;
import com.lhd.qd.module.sys.role.model.vo.RoleListVo;
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
public class RoleServiceImpl extends QdBaseServiceImpl<RoleMapper, RoleDo> implements RoleService {

    @Override
    public IPage<RoleListVo> pageRole(RolePageQuery query) {

        IPage<RoleDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<RoleDo>lambdaQuery()
                        .like(StrUtil.isNotEmpty(query.getRoleName()), RoleDo::getRoleName, query.getRoleName()));

        return AbstractRoleConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @Override
    public RoleDetailVo getRoleById(Long id) {

        RoleDo dataObj = getById(id);

        return AbstractRoleConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void saveRole(RoleSaveDto saveDto) {

        valid(null, saveDto);

        RoleDo dataObj = AbstractRoleConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);
    }

    @Override
    public void updateRole(Long id, RoleSaveDto saveDto) {

        valid(id, saveDto);

        RoleDo dataObj = AbstractRoleConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeRole(Long id) {

        RoleDo dataObj = new RoleDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    public void valid(Long id, RoleSaveDto saveDto) {
        Long count = count(Wrappers.<RoleDo>lambdaQuery()
                .eq(RoleDo::getRoleName, saveDto.getRoleName())
                .ne(id != null, RoleDo::getId, id));
        if (count > 0) {
            throw new BusinessException("角色名称已存在，请更换。");
        }
    }
}
