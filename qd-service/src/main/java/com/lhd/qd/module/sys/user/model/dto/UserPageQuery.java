package com.lhd.qd.module.sys.user.model.dto;

import com.lhd.qd.base.QdBasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString(callSuper = true)
@ApiModel(value = "用户分页查询数据对象")
public class UserPageQuery extends QdBasePageQuery {

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "开始创建时间")
    private String startCreateTime;

    @ApiModelProperty(value = "结束创建时间")
    private String endCreateTime;
}
