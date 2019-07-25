package com.lhd.qd.module.sys.dict.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "字典分页查询对象")
public class DictPageQuery extends QdBasePageQuery {

}
