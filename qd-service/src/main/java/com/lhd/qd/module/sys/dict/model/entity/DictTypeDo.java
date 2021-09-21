package com.lhd.qd.module.sys.dict.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 字典类型
 * </p>
 *
 * @author lhd
 * @since 2019-06-01
 */
@Data
@TableName("sys_dict_type")
public class DictTypeDo extends QdBaseDo {

    /**
     * 编码
     */
    private String typeCode;
    /**
     * 描述
     */
    private String typeDesc;
    /**
     * 备注
     */
    private String remark;

}
