package com.lhd.qd.module.sys.role.model.factory;

import com.lhd.qd.module.sys.role.model.converter.RoleConverter;
import com.lhd.qd.module.sys.role.model.dto.RoleResourceDto;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

/**
 * @author lhd
 */
public class RoleResourceTreeVoFactory extends AbstractTreeVoFactory {
    @Override
    public <T extends ITree> AbstractTreeVo produce(T entity) {
        return RoleConverter.INSTANCE.roleResourceDto2ResourceTreeVo((RoleResourceDto) entity);
    }
}
