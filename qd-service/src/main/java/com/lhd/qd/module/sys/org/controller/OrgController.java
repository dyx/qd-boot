package com.lhd.qd.module.sys.org.controller;

import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.org.model.vo.OrgTreeVO;
import com.lhd.qd.module.sys.org.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 组织 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@RestController
@Api(tags = "组织")
@RequestMapping("org")
public class OrgController extends QdBaseController {

    @Autowired
    private OrgService service;

    @ApiOperation(value = "树", response = OrgTreeVO.class)
    @GetMapping(value = "tree")
    public ApiResult<List<OrgTreeVO>> getTree() {

        return success(service.getTree());
    }

}