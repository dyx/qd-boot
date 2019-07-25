package com.lhd.qd.module.sys.org.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "公司分页查询对象")
public class CompanyPageQuery extends QdBasePageQuery {

}
