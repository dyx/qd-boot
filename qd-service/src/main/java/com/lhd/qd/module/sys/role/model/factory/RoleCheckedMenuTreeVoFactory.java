package com.lhd.qd.module.sys.role.model.factory;

import com.lhd.qd.module.sys.role.model.converter.AbstractRoleConverter;
import com.lhd.qd.module.sys.role.model.dto.RoleCheckedResourceDto;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

/**
 * @author lhd
 */
public class RoleCheckedMenuTreeVoFactory extends AbstractTreeVoFactory {
    @Override
    public <T extends ITree> AbstractTreeVo produce(T entity) {
        return AbstractRoleConverter.INSTANCE.roleCheckedResourceDto2RoleCheckedMenuTreeVo((RoleCheckedResourceDto) entity);
    }
}
