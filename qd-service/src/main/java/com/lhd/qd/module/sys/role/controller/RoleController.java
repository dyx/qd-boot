package com.lhd.qd.module.sys.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.base.QdBasePageQuery;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.data.rule.model.dto.DataRuleSaveDTO;
import com.lhd.qd.module.sys.data.rule.model.vo.DataRuleCheckedTreeVO;
import com.lhd.qd.module.sys.data.rule.service.DataRuleService;
import com.lhd.qd.module.sys.role.model.dto.RoleAssignResourceDTO;
import com.lhd.qd.module.sys.role.model.dto.RolePageQuery;
import com.lhd.qd.module.sys.role.model.dto.RoleSaveDTO;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedResourceVO;
import com.lhd.qd.module.sys.role.model.vo.RoleDetailVO;
import com.lhd.qd.module.sys.role.model.vo.RoleListVO;
import com.lhd.qd.module.sys.role.model.vo.RoleResourceVO;
import com.lhd.qd.module.sys.role.service.RoleResourceService;
import com.lhd.qd.module.sys.role.service.RoleService;
import com.lhd.qd.module.sys.user.model.vo.UserListVO;
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

    @ApiOperation(value = "列表", response = RoleListVO.class)
    @GetMapping
    public ApiResult<IPage<RoleListVO>> getPage(RolePageQuery query) {

        return success(service.pageRole(query));
    }

    @ApiOperation(value = "详情", response = RoleDetailVO.class)
    @GetMapping(value = "{id}")
    public ApiResult<RoleDetailVO> getById(@PathVariable("id") Long id) {

        return success(service.getRoleById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody RoleSaveDTO saveDTO) {

        service.saveRole(saveDTO);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody RoleSaveDTO saveDTO) {

        service.updateRole(id, saveDTO);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeRole(id);

        return success();
    }

    @ApiOperation(value = "获取角色资源", response = RoleResourceVO.class)
    @GetMapping("/{id}/resource")
    public ApiResult<RoleResourceVO> getRoleResource(@PathVariable("id") Long id) {

        return success(roleResourceService.getResourceByRoleId(id));
    }

    @ApiOperation(value = "获取角色资源并设置选中状态", response = RoleCheckedResourceVO.class)
    @GetMapping("/{id}/resource/checked")
    public ApiResult<RoleCheckedResourceVO> getResourceCheckedListByRoleId(@PathVariable("id") Long id) {

        return success(roleResourceService.getCheckedResourceByRoleId(id));
    }

    @ApiOperation(value = "分配资源")
    @PostMapping("/{id}/resource")
    public ApiResult assignResource(@PathVariable("id") Long id, @RequestBody RoleAssignResourceDTO dto) {

        roleResourceService.updateRoleResource(id, dto);

        return success();
    }

    @ApiOperation(value = "获取角色数据权限并设置选中状态", response = DataRuleCheckedTreeVO.class)
    @GetMapping("/{id}/data/checked")
    public ApiResult<List<DataRuleCheckedTreeVO>> getDataCheckedListByRoleId(@PathVariable("id") Long id) {

        return success(dataRuleService.getCheckedTreeByRoleId(id));
    }

    @ApiOperation(value = "分配数据权限")
    @PostMapping("/{id}/data")
    public ApiResult assignData(@PathVariable("id") Long id, @RequestBody List<DataRuleSaveDTO> dtoList) {

        dataRuleService.batchSaveByRoleId(id, dtoList);

        return success();
    }

    @ApiOperation(value = "获取角色下用户", response = UserListVO.class)
    @GetMapping("/{id}/user")
    public ApiResult<IPage<UserListVO>> getUserPageByRoleId(@PathVariable("id") Long id, QdBasePageQuery query) {

        return success(userRoleService.getUserPageByRoleId(id, query));
    }

}