package com.lhd.qd.tree;

/**
 * @author lhd
 */
public abstract class AbstractTreeVoFactory {

    /**
     * 生成实体
     * @param entity
     * @return
     */
    public abstract <T extends ITree> AbstractTreeVO produce(T entity);
}
