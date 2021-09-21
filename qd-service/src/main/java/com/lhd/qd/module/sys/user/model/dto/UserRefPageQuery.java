package com.lhd.qd.module.sys.user.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 */
@Data
@ApiModel(value = "用户参照分页查询数据对象")
public class UserRefPageQuery extends QdBasePageQuery {

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "用户名")
    private String username;
}
