package com.lhd.qd.module.sys.menu.model.factory;

import com.lhd.qd.module.sys.menu.model.converter.AbstractMenuConverter;
import com.lhd.qd.module.sys.menu.model.entity.MenuDo;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

/**
 * @author lhd
 */
public class RouterTreeVoFactory extends AbstractTreeVoFactory {

    @Override
    public <T extends ITree> AbstractTreeVo produce(T entity) {
        return AbstractMenuConverter.INSTANCE.do2RouterTreeVo((MenuDo) entity);
    }
}
