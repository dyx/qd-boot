package com.lhd.qd.base;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础Service
 * @author lhd
 * @param <T>
 */
public interface QdBaseService<T> extends IService<T> {

    /**
     * 根据id删除，并更新其他字段
     * @param entity
     * @return
     */
    boolean removeByIdWithFill(T entity);

    /**
     * 根据id列表批量删除，并更新其他字段
     * @param entity
     * @param idList
     * @return
     */
    boolean removeByIdsWithFill(T entity, Collection<? extends Serializable> idList);
}
