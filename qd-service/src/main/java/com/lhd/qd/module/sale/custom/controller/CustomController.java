package com.lhd.qd.module.sale.custom.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sale.custom.model.dto.CustomPageQuery;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDTO;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVO;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVO;
import com.lhd.qd.module.sale.custom.service.CustomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 客户 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
@RestController
@Api(tags = "客户")
@RequestMapping("custom")
public class CustomController extends QdBaseController {

    @Autowired
    private CustomService service;

    @ApiOperation(value = "列表", response = CustomListVO.class)
    @GetMapping
    public ApiResult<IPage<CustomListVO>> getPage(CustomPageQuery query) {

        return success(service.pageCustom(query));
    }

    @ApiOperation(value = "详情", response = CustomDetailVO.class)
    @GetMapping(value = "{id}")
    public ApiResult<CustomDetailVO> getById(@PathVariable("id") Long id) {

        return success(service.getCustomById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody CustomSaveDTO saveDTO) {

        service.saveCustom(saveDTO);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody CustomSaveDTO saveDTO) {

        service.updateCustom(id, saveDTO);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeCustom(id);

        return success();
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping(value = "batch")
    public ApiResult batchRemove(@RequestBody List<Long> idList) {

        service.batchRemoveCustom(idList);

        return success();
    }
}