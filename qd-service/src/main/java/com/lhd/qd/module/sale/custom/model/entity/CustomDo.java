package com.lhd.qd.module.sale.custom.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Data;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
@Data
@TableName("sale_custom")
public class CustomDo extends QdBaseDo {

    /**
     * 姓名
     */
    private String customName;
    /**
     * 手机
     */
    private String phone;
    /**
     * 所属人id
     */
    private Long ownerId;
    /**
     * 所属部门id
     */
    private Long ownerDeptId;

}
