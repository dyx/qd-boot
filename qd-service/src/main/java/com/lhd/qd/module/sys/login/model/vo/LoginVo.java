package com.lhd.qd.module.sys.login.model.vo;

import com.lhd.qd.module.sys.user.model.vo.UserInfoVo;
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
@ToString
@ApiModel(value = "登录视图对象")
public class LoginVo {

    @ApiModelProperty(value = "凭证")
    private String token;

    @ApiModelProperty(value = "用户信息")
    private UserInfoVo user;
}
