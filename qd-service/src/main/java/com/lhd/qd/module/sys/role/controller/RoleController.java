package com.lhd.qd.module.sys.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.base.QdBasePageQuery;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDto;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVo;
import com.lhd.qd.module.sys.data.rule.service.DataRuleService;
import com.lhd.qd.module.sys.role.model.dto.RoleAssignResourceDto;
import com.lhd.qd.module.sys.role.model.dto.RolePageQuery;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDto;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedResourceVo;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVo;
import com.lhd.qd.module.sys.role.model.vo.RoleListVo;
import com.lhd.qd.module.sys.role.model.vo.RoleResourceVo;
import com.lhd.qd.module.sys.role.service.RoleResourceService;
import com.lhd.qd.module.sys.role.service.RoleService;
import com.lhd.qd.module.sys.user.model.vo.UserListVo;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-05-23
 */
@RestController
@Api(tags = "角色")
@RequestMapping("role")
public class RoleController extends QdBaseController {

    @Autowired
    private RoleService service;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private DataRuleService dataRuleService;

    @ApiOperation(value = "列表", response = RoleListVo.class)
    @GetMapping
    public ApiResult<IPage<RoleListVo>> getPage(RolePageQuery query) {

        return success(service.pageRole(query));
    }

    @ApiOperation(value = "详情", response = RoleDetailVo.class)
    @GetMapping(value = "{id}")
    public ApiResult<RoleDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getRoleById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody RoleSaveDto saveDto) {

        service.saveRole(saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody RoleSaveDto saveDto) {

        service.updateRole(id, saveDto);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeRole(id);

        return success();
    }

    @ApiOperation(value = "获取角色资源", response = RoleResourceVo.class)
    @GetMapping("/{id}/resource")
    public ApiResult<RoleResourceVo> getRoleResource(@PathVariable("id") Long id) {

        return success(roleResourceService.getResourceByRoleId(id));
    }

    @ApiOperation(value = "获取角色资源并设置选中状态", response = RoleCheckedResourceVo.class)
    @GetMapping("/{id}/resource/checked")
    public ApiResult<RoleCheckedResourceVo> getResourceCheckedListByRoleId(@PathVariable("id") Long id) {

        return success(roleResourceService.getCheckedResourceByRoleId(id));
    }

    @ApiOperation(value = "分配资源")
    @PostMapping("/{id}/resource")
    public ApiResult assignResource(@PathVariable("id") Long id, @RequestBody RoleAssignResourceDto dto) {

        roleResourceService.updateRoleResource(id, dto);

        return success();
    }

    @ApiOperation(value = "获取角色数据权限并设置选中状态", response = DataRuleCheckedTreeVo.class)
    @GetMapping("/{id}/data/checked")
    public ApiResult<List<DataRuleCheckedTreeVo>> getDataCheckedListByRoleId(@PathVariable("id") Long id) {

        return success(dataRuleService.getCheckedTreeByRoleId(id));
    }

    @ApiOperation(value = "分配数据权限")
    @PostMapping("/{id}/data")
    public ApiResult assignData(@PathVariable("id") Long id, @RequestBody List<DataRuleSaveDto> dtoList) {

        dataRuleService.batchSaveByRoleId(id, dtoList);

        return success();
    }

    @ApiOperation(value = "获取角色下用户", response = UserListVo.class)
    @GetMapping("/{id}/user")
    public ApiResult<IPage<UserListVo>> getUserPageByRoleId(@PathVariable("id") Long id, QdBasePageQuery query) {

        return success(userRoleService.getUserPageByRoleId(id, query));
    }

}
