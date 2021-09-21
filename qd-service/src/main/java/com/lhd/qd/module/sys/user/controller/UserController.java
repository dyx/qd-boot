package com.lhd.qd.module.sys.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.user.model.dto.UserPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserRefPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDto;
import com.lhd.qd.module.sys.user.model.vo.UserAssignRoleVo;
import com.lhd.qd.module.sys.user.model.vo.UserDetailVo;
import com.lhd.qd.module.sys.user.model.vo.UserListVo;
import com.lhd.qd.module.sys.user.model.vo.UserRefListVo;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import com.lhd.qd.module.sys.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhd
 */
@RestController
@Api(tags = "用户")
@RequestMapping("user")
public class UserController extends QdBaseController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "列表", response = UserListVo.class)
    @GetMapping
    public ApiResult<IPage<UserListVo>> getList(UserPageQuery query) {

        return success(service.pageUser(query));
    }

    @ApiOperation(value = "参照列表", response = UserRefListVo.class)
    @GetMapping(value = "ref")
    public ApiResult<IPage<UserRefListVo>> getRefList(UserRefPageQuery query) {

        return success(service.pageRefUser(query));
    }

    @ApiOperation(value = "详情", response = UserDetailVo.class)
    @GetMapping(value = "{id}")
    public ApiResult<UserDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getUserById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult save(@Validated @RequestBody UserSaveDto saveDto) {

        service.saveUser(saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult update(@PathVariable("id") Long id, @Validated @RequestBody UserSaveDto saveDto) {

        service.updateUser(id, saveDto);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult remove(@PathVariable("id") Long id) {

        service.removeUser(id);

        return success();
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping(value = "batch")
    public ApiResult batchRemove(@RequestBody List<Long> idList) {

        service.batchRemoveUser(idList);

        return success();
    }

    @ApiOperation(value = "获取用户分配角色列表", response = UserAssignRoleVo.class)
    @GetMapping(value = "{id}/role/assign")
    public ApiResult<List<UserAssignRoleVo>> getAssignRoleList(@PathVariable("id") Long id) {

        return success(userRoleService.getRoleListWithChecked(id));
    }

    @ApiOperation(value = "用户分配角色")
    @PutMapping(value = "{id}/role/assign")
    public ApiResult getAssignRoleList(@PathVariable("id") Long id, @RequestBody List<Long> roleIdList) {

        userRoleService.updateUserAssignRole(id, roleIdList);

        return success();
    }
}
