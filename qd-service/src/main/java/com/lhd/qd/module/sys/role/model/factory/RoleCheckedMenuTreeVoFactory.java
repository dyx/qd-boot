package com.lhd.qd.module.sys.role.model.factory;

import com.lhd.qd.module.sys.role.model.converter.AbstractRoleConverter;
import com.lhd.qd.module.sys.role.model.dto.RoleCheckedResourceDTO;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

/**
 * @author lhd
 */
public class RoleCheckedMenuTreeVoFactory extends AbstractTreeVoFactory {
    @Override
    public <T extends ITree> AbstractTreeVO produce(T entity) {
        return AbstractRoleConverter.INSTANCE.roleCheckedResourceDTO2RoleCheckedMenuTreeVO((RoleCheckedResourceDTO) entity);
    }
}
