package com.lhd.qd.module.sys.org.controller;

import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDto;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVo;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVo;
import com.lhd.qd.module.sys.org.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公司 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@RestController
@Api(tags = "公司")
@RequestMapping("org/company")
public class CompanyController extends QdBaseController {

    @Autowired
    private CompanyService service;

    @ApiOperation(value = "树结构", response = CompanyTreeVo.class)
    @GetMapping(value = "tree")
    public ApiResult<List<CompanyTreeVo>> getTree() {

        return success(service.getCompanyTree());
    }

    @ApiOperation(value = "参照树结构", response = CompanyTreeVo.class)
    @GetMapping(value = "ref/tree")
    public ApiResult<List<CompanyTreeVo>> getRefTree() {

        return success(service.getCompanyTree());
    }

    @ApiOperation(value = "详情", response = CompanyDetailVo.class)
    @GetMapping(value = "{id}")
    public ApiResult<CompanyDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getCompanyById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody CompanySaveDto saveDto) {

        service.saveCompany(saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody CompanySaveDto saveDto) {

        service.updateCompany(id, saveDto);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeCompany(id);

        return success();
    }
}
