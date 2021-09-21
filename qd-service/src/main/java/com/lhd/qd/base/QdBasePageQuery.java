package com.lhd.qd.base;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import com.lhd.qd.constant.SortOrderEnum;
import com.lhd.qd.exception.BusinessException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString
public class QdBasePageQuery implements Serializable {

    private final static Integer DEFAULT_PAGE = 1;
    private final static Integer DEFAULT_SIZE = 10;
    private final static Integer MAX_SIZE = 100;

    @ApiModelProperty(value = "当前页数")
    @Setter
    private Integer page;

    @ApiModelProperty(value = "每页记录数")
    @Setter
    private Integer size;

    @ApiModelProperty(value = "排序字段")
    @Setter
    @Getter
    private String sortField;

    @ApiModelProperty(value = "排序顺序")
    @Getter
    private String sortOrder;

    public Integer getPage() {

        if (page == null || page < DEFAULT_PAGE) {
            page = DEFAULT_PAGE;
        }

        return page;
    }

    public Integer getSize() {

        if (size == null || size < DEFAULT_SIZE) {
            size = DEFAULT_SIZE;
        }

        if (size > MAX_SIZE) {
            size = MAX_SIZE;
        }

        return size;
    }

    public void setSortOrder(String sortOrder) {

        if (StrUtil.isNotEmpty(sortOrder)
                && !EnumUtil.contains(SortOrderEnum.class, sortOrder.toUpperCase())) {
            throw new BusinessException("排序规则参数值错误");
        }

        this.sortOrder = sortOrder;
    }
}
