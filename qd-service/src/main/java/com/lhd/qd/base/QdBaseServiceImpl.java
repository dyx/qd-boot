package com.lhd.qd.base;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.lhd.qd.constant.SortOrderEnum;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础Service实现
 * @author lhd
 * @param <M>
 * @param <T>
 */
public class QdBaseServiceImpl<M extends QdBaseMapper<T>, T> extends ServiceImpl<M, T> implements QdBaseService<T> {

    @Override
    public boolean removeByIdWithFill(T entity) {
        return SqlHelper.retBool(baseMapper.deleteByIdWithFill(entity));
    }

    @Override
    public boolean removeByIdsWithFill(T entity, Collection<? extends Serializable> idList) {
        return SqlHelper.retBool(baseMapper.deleteByIdsWithFill(entity, idList));
    }

    protected boolean isOrderBy(QdBasePageQuery query, String field) {

        if (query == null || StrUtil.isEmpty(query.getSortField()) || !field.equals(query.getSortField())) {
            return false;
        }


        SortOrderEnum sortOrderEnum = EnumUtil.getEnumMap(SortOrderEnum.class).get(query.getSortOrder().toUpperCase());
        return sortOrderEnum != null && sortOrderEnum != SortOrderEnum.NORMAL;
    }

    protected boolean isAsc(QdBasePageQuery query) {

        if (query == null || StrUtil.isEmpty(query.getSortOrder())) {
            return false;
        }

        return EnumUtil.getEnumMap(SortOrderEnum.class).get(query.getSortOrder().toUpperCase()) == SortOrderEnum.ASC;
    }
}
