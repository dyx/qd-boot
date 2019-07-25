package com.lhd.qd.module.sys.menu.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-25
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "菜单分页查询对象")
public class MenuPageQuery extends QdBasePageQuery {

}
