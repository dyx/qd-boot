package com.lhd.qd.module.sys.org.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhd.qd.base.QdBaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 公司
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("sys_company")
public class CompanyDo extends QdBaseDo {

    /**
     * 上级id
     */
    private Long parentId;
    /**
     * 名称
     */
    private String companyName;

}
