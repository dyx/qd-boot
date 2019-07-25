package com.lhd.qd.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础Mapper
 * @author lhd
 * @param <T>
 */
public interface QdBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据 id 逻辑删除数据,并带字段填充功能
     * @param entity
     * @return
     */
    int deleteByIdWithFill(T entity);

    /**
     * 根据id列表，批量删除数据，并带字段填充功能
     * @param entity
     * @param idList
     * @return
     */
    int deleteByIdsWithFill(@Param(Constants.ENTITY) T entity, @Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
}
