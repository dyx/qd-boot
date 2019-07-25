package com.lhd.qd.module.sys.trans.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
public interface TransMapper {

    /**
     * 根据id查找name
     * @param tableName
     * @param queryWrapper
     * @return
     */
    List<Map<String, Object>> selectNamesByIds(@Param("tableName") String tableName,
                                               @Param(Constants.WRAPPER) LambdaQueryWrapper queryWrapper);
}
