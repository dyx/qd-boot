package com.lhd.qd.module.sale.custom.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import com.lhd.qd.base.QdBaseDo;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
@Setter
@Getter
@ToString(callSuper = true)
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
