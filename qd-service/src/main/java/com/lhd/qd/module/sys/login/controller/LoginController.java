package com.lhd.qd.module.sys.login.controller;

import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.login.model.dto.LoginDTO;
import com.lhd.qd.module.sys.login.model.vo.LoginVO;
import com.lhd.qd.module.sys.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@Slf4j
@Api(tags = "登录")
@RestController
public class LoginController extends QdBaseController {

    @Autowired
    private LoginService service;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ApiResult<LoginVO> login(@RequestBody LoginDTO dto) {

        return success(service.login(dto));
    }

    @ApiOperation(value = "登出")
    @PostMapping("logout")
    public ApiResult logout() {

        service.logout();

        return success();
    }

    @GetMapping("401")
    public void unauthorized() {

    }
}
