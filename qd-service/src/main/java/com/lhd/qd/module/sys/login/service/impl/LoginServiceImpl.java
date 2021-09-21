package com.lhd.qd.module.sys.login.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.config.QdProperty;
import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.login.model.dto.LoginDto;
import com.lhd.qd.module.sys.login.model.dto.TokenDto;
import com.lhd.qd.module.sys.login.model.vo.LoginVo;
import com.lhd.qd.module.sys.login.service.LoginService;
import com.lhd.qd.module.sys.org.service.DeptService;
import com.lhd.qd.module.sys.role.service.RoleResourceService;
import com.lhd.qd.module.sys.user.dao.UserMapper;
import com.lhd.qd.module.sys.user.model.converter.AbstractUserConverter;
import com.lhd.qd.module.sys.user.model.entity.UserDo;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVo;
import com.lhd.qd.module.sys.user.model.vo.UserInfoVo;
import com.lhd.qd.module.sys.user.model.vo.UserRoleVo;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lhd.qd.constant.CommonConsts.SUPER_ADMIN_USER_ID;
import static java.util.stream.Collectors.toList;


/**
 * @author lhd
 */
@Service
public class LoginServiceImpl extends QdBaseServiceImpl<UserMapper, UserDo> implements LoginService {

    @Autowired
    private QdProperty qdProperty;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private DeptService deptService;

    @Override
    public LoginVo login(LoginDto dto) {

        UserDo dataObj = getOne(Wrappers.<UserDo>lambdaQuery().eq(UserDo::getUsername, dto.getUsername()));
        if (dataObj == null
                || !SecurityUtils.md5WithSalt(dto.getPwd(), dataObj.getSalt(), qdProperty.getSaltTimes()).equals(dataObj.getPwd())) {
            throw new BusinessException("用户名或密码错误");
        }

        if (dataObj.getCompanyId() == null || dataObj.getDeptId() == null) {
            throw new BusinessException("用户还未完善组织信息");
        }

        Long userId = dataObj.getId();
        UserInfoVo userVo = AbstractUserConverter.INSTANCE.do2InfoVo(dataObj);

        List<UserRoleVo> userRoleVoList = userRoleService.getRoleListByUserId(userId);
        if (userRoleVoList == null || userRoleVoList.size() == 0) {
            throw new BusinessException("用户还未分配角色");
        }
        userVo.setRoleList(userRoleService.getRoleListByUserId(userId));


        List<Long> roleIdList = userRoleVoList.stream().map(UserRoleVo::getId).collect(toList());
        List<AbstractTreeVo> menuTreeVoList = roleResourceService.getMenuTreeByRoleIdList(roleIdList);
        if (menuTreeVoList == null || menuTreeVoList.size() == 0) {
            throw new BusinessException("用户还未分配菜单");
        }
        userVo.setMenuList(menuTreeVoList);

        // url权限列表
        List<String> permissionList = roleResourceService.getPermissionListByRoleIdList(roleIdList);
        // 前端页面权限
        userVo.setPageElementMap(roleResourceService.getPageElementMapByRoleIdList(roleIdList));


        String clientId = UuidUtils.getId();
        // 生成token
        String token = JwtUtils.generate(new TokenDto(dataObj.getId(), clientId),
                dataObj.getSalt(),
                DateUtil.date().offset(DateField.MINUTE, qdProperty.getTokenExpiresMinutes()));
        long expireSeconds = qdProperty.getTokenExpiresMinutes() * 60;


        // 缓存token
        String userTokenKey = RedisConsts.getUserTokenKey(clientId, dataObj.getId());
        redisUtils.setValue(userTokenKey, token, expireSeconds);

        // 缓存用户信息
        String userKey = RedisConsts.getUserKey(dataObj.getId());
        redisUtils.setHash(userKey, UserCacheVo.ID, dataObj.getId());
        redisUtils.setHash(userKey, UserCacheVo.USERNAME, dataObj.getUsername());
        redisUtils.setHash(userKey, UserCacheVo.SALT, dataObj.getSalt());
        redisUtils.setHash(userKey, UserCacheVo.COMPANY_ID, dataObj.getCompanyId());
        redisUtils.setHash(userKey, UserCacheVo.DEPT_ID, dataObj.getDeptId());
        redisUtils.setHash(userKey, UserCacheVo.PERMISSION_LIST, permissionList);
        redisUtils.setHash(userKey, UserCacheVo.ROLE_ID_LIST, roleIdList);

        if (!SUPER_ADMIN_USER_ID.equals(dataObj.getId())) {
            // 数据权限相关
            redisUtils.setHash(userKey, UserCacheVo.COMPANY_AND_SUB_COMPANY_DEPT_IDS,
                    deptService.getCompanyAndSubCompanyDeptIds(dataObj.getCompanyId()));
            redisUtils.setHash(userKey, UserCacheVo.COMPANY_DEPT_IDS,
                    deptService.getCompanyDeptIds(dataObj.getCompanyId()));
            redisUtils.setHash(userKey, UserCacheVo.DEPT_AND_SUB_DEPT_DEPT_IDS,
                    deptService.getDeptAndSubDeptDeptIds(dataObj.getDeptId()));
        }

        redisUtils.expire(userKey, expireSeconds);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setUser(userVo);

        return loginVo;
    }

    @Override
    public void logout() {

        // 缓存token
        String userTokenKey = RedisConsts.getUserTokenKey(UserUtils.getClientId(), UserUtils.getUserId());
        redisUtils.delValue(userTokenKey);
    }
}
