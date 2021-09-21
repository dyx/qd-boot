package com.lhd.qd.module.sys.menu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.menu.model.dto.PageElementPageQuery;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDto;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVo;
import com.lhd.qd.module.sys.menu.service.PageElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 页面元素 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@RestController
@Api(tags = "页面元素")
@RequestMapping("menu")
public class PageElementController extends QdBaseController {

    @Autowired
    private PageElementService service;

    @ApiOperation(value = "列表", response = PageElementListVo.class)
    @GetMapping("{menuId}/page-element")
    public ApiResult<IPage<PageElementListVo>> getPageByMenuId(@PathVariable("menuId") Long menuId, PageElementPageQuery query) {

        return success(service.pagePageElementByMenuId(menuId, query));
    }

    @ApiOperation(value = "详情", response = PageElementDetailVo.class)
    @GetMapping(value = "page-element/{id}")
    public ApiResult<PageElementDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getPageElementById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping("{menuId}/page-element")
    public ApiResult save(@PathVariable("menuId") Long menuId, @Validated @RequestBody PageElementSaveDto saveDto) {

        service.savePageElement(menuId, saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "page-element/{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody PageElementSaveDto saveDto) {

        service.updatePageElement(id, saveDto);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "page-element/{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removePageElement(id);

        return success();
    }
}
