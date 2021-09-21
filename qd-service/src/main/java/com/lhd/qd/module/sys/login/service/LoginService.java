package com.lhd.qd.module.sys.login.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.login.model.dto.LoginDto;
import com.lhd.qd.module.sys.login.model.vo.LoginVo;
import com.lhd.qd.module.sys.user.model.entity.UserDo;

/**
 * @author lhd
 */
public interface LoginService extends QdBaseService<UserDo> {

    /**
     * 登录
     * @param dto
     * @return
     */
    LoginVo login(LoginDto dto);

    /**
     * 登出
     */
    void logout();
}
