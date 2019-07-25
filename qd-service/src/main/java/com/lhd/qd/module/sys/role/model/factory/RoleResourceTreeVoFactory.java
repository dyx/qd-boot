package com.lhd.qd.module.sys.role.model.factory;

import com.lhd.qd.module.sys.role.model.converter.AbstractRoleConverter;
import com.lhd.qd.module.sys.role.model.dto.RoleResourceDTO;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

/**
 * @author lhd
 */
public class RoleResourceTreeVoFactory extends AbstractTreeVoFactory {
    @Override
    public <T extends ITree> AbstractTreeVO produce(T entity) {
        return AbstractRoleConverter.INSTANCE.roleResourceDTO2ResourceTreeVO((RoleResourceDTO) entity);
    }
}
