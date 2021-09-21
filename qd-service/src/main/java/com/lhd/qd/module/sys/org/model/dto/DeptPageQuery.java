package com.lhd.qd.module.sys.org.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Data
@ApiModel(value = "部门分页查询对象")
public class DeptPageQuery extends QdBasePageQuery {

}
