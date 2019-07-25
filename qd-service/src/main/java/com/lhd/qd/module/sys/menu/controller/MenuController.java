package com.lhd.qd.module.sys.menu.controller;

import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDTO;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVO;
import com.lhd.qd.module.sys.menu.model.vo.ResourceTreeVO;
import com.lhd.qd.module.sys.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@RestController
@Api(tags = "菜单")
@RequestMapping("menu")
public class MenuController extends QdBaseController {

    @Autowired
    private MenuService service;

    @ApiOperation(value = "所有菜单树", response = ResourceTreeVO.class)
    @GetMapping("/tree")
    public ApiResult<List<AbstractTreeVO>> getTree() {

        return success(service.getMenuTree());
    }

    @ApiOperation(value = "详情", response = MenuDetailVO.class)
    @GetMapping(value = "{id}")
    public ApiResult<MenuDetailVO> getById(@PathVariable("id") Long id) {

        return success(service.getMenuById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody MenuSaveDTO saveDTO) {

        return success(service.saveMenu(saveDTO));
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody MenuSaveDTO saveDTO) {

        service.updateMenu(id, saveDTO);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeMenu(id);

        return success();
    }
}