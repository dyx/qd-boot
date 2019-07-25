package com.lhd.qd.module.sys.trans.model.dto;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 翻译传输对象
 * 查询L中的id和name，根据M的id，赋值给N的name
 * @author lhd
 */
@Setter
@Getter
@ToString
public class TransDTO<L, M, N> {

    /**
     * 要查询表的DO，为空代表使用自定义值匹配
     */
    private Class<L> doClass;
    /**
     * 查询条件id列
     */
    private SFunction<L, ?> idColumn;
    /**
     * 查询条件id值
     */
    private Set idValues;
    /**
     * 要查询的name列
     */
    private SFunction<L, ?> nameColumn;

    /**
     * 读取字段
     */
    private SFunction<M, ?> readField;
    /**
     * 写入字段
     */
    private SFunction<N, ?> writeField;
    /**
     * 写入字段类型
     */
    private Class writeMethodParamType;

    /**
     * 自定义值
     */
    private List<Map<String, Object>> customValue;

    /**
     * 根据自定义值匹配
     * @param readField
     * @param writeField
     * @param customValue
     */
    public TransDTO(SFunction<M, ?> readField, SFunction<N, ?> writeField, List<Map<String, Object>> customValue) {
        this(null, null, null, null, readField, writeField, String.class, customValue);
    }

    /**
     * 根据自定义值匹配
     * @param readField
     * @param writeField
     * @param writeMethodParamType
     * @param customValue
     */
    public TransDTO(SFunction<M, ?> readField, SFunction<N, ?> writeField,
                    Class writeMethodParamType,
                    List<Map<String, Object>> customValue) {
        this(null, null, null, null, readField, writeField, writeMethodParamType, customValue);
    }

    public TransDTO(Class<L> doClass, SFunction<L, ?> idColumn, Set idValues, SFunction<L, ?> nameColumn,
                    SFunction<M, ?> readField,
                    SFunction<N, ?> writeField) {
        this(doClass, idColumn, idValues, nameColumn, readField, writeField, String.class, null);
    }

    public TransDTO(Class<L> doClass, SFunction<L, ?> idColumn, Set idValues, SFunction<L, ?> nameColumn,
                    SFunction<M, ?> readField,
                    SFunction<N, ?> writeField,
                    Class writeMethodParamType) {
        this(doClass, idColumn, idValues, nameColumn, readField, writeField, writeMethodParamType, null);
    }

    public TransDTO(Class<L> doClass, SFunction<L, ?> idColumn, Set idValues, SFunction<L, ?> nameColumn,
                    SFunction<M, ?> readField,
                    SFunction<N, ?> writeField,
                    Class writeMethodParamType,
                    List<Map<String, Object>> customValue) {
        this.doClass = doClass;
        this.idColumn = idColumn;
        this.idValues = idValues;
        this.nameColumn = nameColumn;
        this.readField = readField;
        this.writeField = writeField;
        this.writeMethodParamType = writeMethodParamType;
        this.customValue = customValue;
    }

}
