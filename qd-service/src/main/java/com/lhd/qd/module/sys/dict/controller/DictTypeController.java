package com.lhd.qd.module.sys.dict.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.dict.model.dto.DictTypePageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDto;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVo;
import com.lhd.qd.module.sys.dict.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典类型 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-06-01
 */
@RestController
@Api(tags = "字典类型")
@RequestMapping("dict/type")
public class DictTypeController extends QdBaseController {

    @Autowired
    private DictTypeService service;

    @ApiOperation(value = "列表", response = DictTypeListVo.class)
    @GetMapping
    public ApiResult<IPage<DictTypeListVo>> getPage(DictTypePageQuery query) {

        return success(service.pageDictType(query));
    }

    @ApiOperation(value = "详情", response = DictTypeDetailVo.class)
    @GetMapping(value = "{id}")
    public ApiResult<DictTypeDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getDictTypeById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult<?> save(@Validated @RequestBody DictTypeSaveDto saveDto) {

        service.saveDictType(saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult<?> update(@PathVariable("id") Long id, @Validated @RequestBody DictTypeSaveDto saveDto) {

        service.updateDictType(id, saveDto);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult<?> remove(@PathVariable("id") Long id) {

        service.removeDictType(id);

        return success();
    }
}
