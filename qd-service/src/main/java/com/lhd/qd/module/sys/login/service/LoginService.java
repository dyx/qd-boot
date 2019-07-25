package com.lhd.qd.module.sys.login.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.login.model.dto.LoginDTO;
import com.lhd.qd.module.sys.login.model.vo.LoginVO;
import com.lhd.qd.module.sys.user.model.entity.UserDO;

/**
 * @author lhd
 */
public interface LoginService extends QdBaseService<UserDO> {

    /**
     * 登录
     * @param dto
     * @return
     */
    LoginVO login(LoginDTO dto);

    /**
     * 登出
     */
    void logout();
}
