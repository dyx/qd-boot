package com.lhd.qd.module.sys.login.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.config.QdProperty;
import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.login.model.dto.LoginDTO;
import com.lhd.qd.module.sys.login.model.dto.TokenDTO;
import com.lhd.qd.module.sys.login.model.vo.LoginVO;
import com.lhd.qd.module.sys.login.service.LoginService;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.module.sys.org.service.DeptService;
import com.lhd.qd.module.sys.role.service.RoleResourceService;
import com.lhd.qd.module.sys.user.dao.UserMapper;
import com.lhd.qd.module.sys.user.model.converter.AbstractUserConverter;
import com.lhd.qd.module.sys.user.model.entity.UserDO;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVO;
import com.lhd.qd.module.sys.user.model.vo.UserInfoVO;
import com.lhd.qd.module.sys.user.model.vo.UserRoleVO;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import com.lhd.qd.util.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.lhd.qd.constant.CommonConsts.SUPER_ADMIN_USER_ID;
import static java.util.stream.Collectors.toList;


/**
 * @author lhd
 */
@Service
public class LoginServiceImpl extends QdBaseServiceImpl<UserMapper, UserDO> implements LoginService {

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
    public LoginVO login(LoginDTO dto) {

        UserDO dataObj = getOne(Wrappers.<UserDO>lambdaQuery().eq(UserDO::getUsername, dto.getUsername()));
        if (dataObj == null
                || !SecurityUtils.md5WithSalt(dto.getPwd(), dataObj.getSalt(), qdProperty.getSaltTimes()).equals(dataObj.getPwd())) {
            throw new BusinessException("用户名或密码错误");
        }

        if (dataObj.getCompanyId() == null || dataObj.getDeptId() == null) {
            throw new BusinessException("用户还未完善组织信息");
        }

        Long userId = dataObj.getId();
        UserInfoVO userVO = AbstractUserConverter.INSTANCE.do2InfoVO(dataObj);

        List<UserRoleVO> userRoleVOList = userRoleService.getRoleListByUserId(userId);
        if (userRoleVOList == null || userRoleVOList.size() == 0) {
            throw new BusinessException("用户还未分配角色");
        }
        userVO.setRoleList(userRoleService.getRoleListByUserId(userId));


        List<Long> roleIdList = userRoleVOList.stream().map(UserRoleVO::getId).collect(toList());
        List<AbstractTreeVO> menuTreeVOList = roleResourceService.getMenuTreeByRoleIdList(roleIdList);
        if (menuTreeVOList == null || menuTreeVOList.size() == 0) {
            throw new BusinessException("用户还未分配菜单");
        }
        userVO.setMenuList(menuTreeVOList);

        // url权限列表
        List<String> permissionList = roleResourceService.getPermissionListByRoleIdList(roleIdList);
        // 前端页面权限
        userVO.setPageElementMap(roleResourceService.getPageElementMapByRoleIdList(roleIdList));


        String clientId = UuidUtils.getId();
        // 生成token
        String token = JwtUtils.generate(new TokenDTO(dataObj.getId(), clientId),
                dataObj.getSalt(),
                DateUtils.addMinutes(new Date(), qdProperty.getTokenExpiresMinutes()));
        long expireSeconds = qdProperty.getTokenExpiresMinutes() * 60;


        // 缓存token
        String userTokenKey = RedisConsts.getUserTokenKey(clientId, dataObj.getId());
        redisUtils.setValue(userTokenKey, token, expireSeconds);

        // 缓存用户信息
        String userKey = RedisConsts.getUserKey(dataObj.getId());
        redisUtils.setHash(userKey, UserCacheVO.ID, dataObj.getId());
        redisUtils.setHash(userKey, UserCacheVO.USERNAME, dataObj.getUsername());
        redisUtils.setHash(userKey, UserCacheVO.SALT, dataObj.getSalt());
        redisUtils.setHash(userKey, UserCacheVO.COMPANY_ID, dataObj.getCompanyId());
        redisUtils.setHash(userKey, UserCacheVO.DEPT_ID, dataObj.getDeptId());
        redisUtils.setHash(userKey, UserCacheVO.PERMISSION_LIST, permissionList);
        redisUtils.setHash(userKey, UserCacheVO.ROLE_ID_LIST, roleIdList);

        if (!SUPER_ADMIN_USER_ID.equals(dataObj.getId())) {
            // 数据权限相关
            redisUtils.setHash(userKey, UserCacheVO.COMPANY_AND_SUB_COMPANY_DEPT_IDS,
                    deptService.getCompanyAndSubCompanyDeptIds(dataObj.getCompanyId()));
            redisUtils.setHash(userKey, UserCacheVO.COMPANY_DEPT_IDS,
                    deptService.getCompanyDeptIds(dataObj.getCompanyId()));
            redisUtils.setHash(userKey, UserCacheVO.DEPT_AND_SUB_DEPT_DEPT_IDS,
                    deptService.getDeptAndSubDeptDeptIds(dataObj.getDeptId()));
        }

        redisUtils.expire(userKey, expireSeconds);

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUser(userVO);

        return loginVO;
    }

    @Override
    public void logout() {

        // 缓存token
        String userTokenKey = RedisConsts.getUserTokenKey(UserUtils.getClientId(), UserUtils.getUserId());
        redisUtils.delValue(userTokenKey);
    }
}
