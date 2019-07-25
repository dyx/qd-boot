package com.lhd.qd.module.sys.menu.model.factory;

import com.lhd.qd.module.sys.menu.model.converter.AbstractMenuConverter;
import com.lhd.qd.module.sys.menu.model.entity.MenuDO;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

/**
 * @author lhd
 */
public class ResourceTreeVoFactory extends AbstractTreeVoFactory {

    @Override
    public <T extends ITree> AbstractTreeVO produce(T entity) {
        return AbstractMenuConverter.INSTANCE.do2ResourceTreeVO((MenuDO) entity);
    }
}
