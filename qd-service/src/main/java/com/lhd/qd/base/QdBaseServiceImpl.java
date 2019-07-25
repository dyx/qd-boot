package com.lhd.qd.base;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.lhd.qd.constant.SortOrderEnum;
import com.lhd.qd.util.LambdaConvertUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

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

        if (query == null || StringUtils.isEmpty(query.getSortField()) || !field.equals(query.getSortField())) {
            return false;
        }

        SortOrderEnum sortOrderEnum = EnumUtils.getEnumIgnoreCase(SortOrderEnum.class, query.getSortOrder());
        return sortOrderEnum != null && sortOrderEnum != SortOrderEnum.NORMAL;
    }

    protected boolean isOrderBy(QdBasePageQuery query, SFunction<T, ?> field) {

        if (query == null || StringUtils.isEmpty(query.getSortField())
                || !LambdaConvertUtils.lambdaToProperty(field).equals(query.getSortField())) {
            return false;
        }

        SortOrderEnum sortOrderEnum = EnumUtils.getEnumIgnoreCase(SortOrderEnum.class, query.getSortOrder());
        return sortOrderEnum != null && sortOrderEnum != SortOrderEnum.NORMAL;
    }

    protected boolean isAsc(QdBasePageQuery query) {

        if (query == null) {
            return false;
        }

        return EnumUtils.getEnumIgnoreCase(SortOrderEnum.class, query.getSortOrder()) == SortOrderEnum.ASC;
    }
}
