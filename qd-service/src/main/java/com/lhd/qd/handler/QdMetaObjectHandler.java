package com.lhd.qd.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lhd.qd.util.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 新增修改时自动填充字段值
 * @author lhd
 */
@Component
public class QdMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "createUserId", Long.class, UserUtils.getUserId());
        strictUpdateFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updateUserId", Long.class, UserUtils.getUserId());
        strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
