package com.lhd.qd.tree;

/**
 * @author lhd
 */
public interface ITree {

    /**
     * 主键
     * @return
     */
    Long getId();

    /**
     * 上级主键
     * @return
     */
    Long getParentId();
}
