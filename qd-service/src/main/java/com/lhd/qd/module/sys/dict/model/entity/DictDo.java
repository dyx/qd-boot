package com.lhd.qd.module.sys.dict.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author lhd
 * @since 2019-05-31
 */
@Data
@TableName("sys_dict")
public class DictDo extends QdBaseDo {

    /**
     * 类型
     */
    private String typeCode;
    /**
     * 值
     */
    private Integer dictValue;
    /**
     * 描述
     */
    private String dictDesc;
    /**
     * 顺序
     */
    private Integer sortNum;
}
