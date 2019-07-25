package com.lhd.qd.handler;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * 批量逻辑删除填充更新字段
 * @author lhd
 */
public class LogicDeleteByIdsWithFill extends AbstractMethod {

    /**
     * mapper 对应的方法名
     */
    private static final String MAPPER_METHOD = "deleteByIdsWithFill";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BATCH_BY_IDS;
        if (tableInfo.isLogicDelete()) {
            // 过滤出填充字段
            List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream()
                .filter(i -> i.getFieldFill() == FieldFill.UPDATE || i.getFieldFill() == FieldFill.INSERT_UPDATE)
                .collect(toList());
            if (CollectionUtils.isNotEmpty(fieldInfos)) {
                // 填充字段逻辑删除
                String sqlSet = "SET " + fieldInfos.stream().map(i -> i.getSqlSet(ENTITY_DOT)).collect(joining(EMPTY))
                    + tableInfo.getLogicDeleteSql(false, true);
                sql = String.format(sqlMethod.getSql(),
                        tableInfo.getTableName(), sqlSet,
                        tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA),
                        tableInfo.getLogicDeleteSql(true, false));
            } else {
                // 逻辑删除
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlLogicSet(tableInfo),
                    tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA),
                    tableInfo.getLogicDeleteSql(true, false));
            }
        } else {
            // 删除
            sqlMethod = SqlMethod.DELETE_BATCH_BY_IDS;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                    SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA));
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, MAPPER_METHOD, sqlSource);
    }
}
