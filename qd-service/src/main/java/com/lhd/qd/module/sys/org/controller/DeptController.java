package com.lhd.qd.module.sys.org.controller;

import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDto;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVo;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVo;
import com.lhd.qd.module.sys.org.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@RestController
@Api(tags = "部门")
@RequestMapping("org/dept")
public class DeptController extends QdBaseController {

    @Autowired
    private DeptService service;

    @ApiOperation(value = "树", response = DeptTreeVo.class)
    @GetMapping(value = "tree/company/{companyId}")
    public ApiResult<List<DeptTreeVo>> getTreeByCompanyId(@PathVariable("companyId") Long companyId) {

        return success(service.getDeptTreeByCompanyId(companyId));
    }

    @ApiOperation(value = "根据公司获取参照树", response = DeptTreeVo.class)
    @GetMapping(value = "ref/tree/company/{companyId}")
    public ApiResult<List<DeptTreeVo>> getRefTreeByCompanyId(@PathVariable("companyId") Long companyId) {

        return success(service.getDeptRefTreeByCompanyId(companyId));
    }

    @ApiOperation(value = "详情", response = DeptDetailVo.class)
    @GetMapping(value = "{id}")
    public ApiResult<DeptDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getDeptById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody DeptSaveDto saveDto) {

        service.saveDept(saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody DeptSaveDto saveDto) {

        service.updateDept(id, saveDto);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeDept(id);

        return success();
    }
}
