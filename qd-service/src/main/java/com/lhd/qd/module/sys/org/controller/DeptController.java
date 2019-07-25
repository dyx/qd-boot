package com.lhd.qd.module.sys.org.controller;

import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDTO;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVO;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVO;
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

    @ApiOperation(value = "树", response = DeptTreeVO.class)
    @GetMapping(value = "tree/company/{companyId}")
    public ApiResult<List<DeptTreeVO>> getTreeByCompanyId(@PathVariable("companyId") Long companyId) {

        return success(service.getDeptTreeByCompanyId(companyId));
    }

    @ApiOperation(value = "根据公司获取参照树", response = DeptTreeVO.class)
    @GetMapping(value = "ref/tree/company/{companyId}")
    public ApiResult<List<DeptTreeVO>> getRefTreeByCompanyId(@PathVariable("companyId") Long companyId) {

        return success(service.getDeptRefTreeByCompanyId(companyId));
    }

    @ApiOperation(value = "详情", response = DeptDetailVO.class)
    @GetMapping(value = "{id}")
    public ApiResult<DeptDetailVO> getById(@PathVariable("id") Long id) {

        return success(service.getDeptById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody DeptSaveDTO saveDTO) {

        service.saveDept(saveDTO);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody DeptSaveDTO saveDTO) {

        service.updateDept(id, saveDTO);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeDept(id);

        return success();
    }
}