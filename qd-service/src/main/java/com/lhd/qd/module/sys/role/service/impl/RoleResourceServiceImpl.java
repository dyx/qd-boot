package com.lhd.qd.module.sys.role.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.constant.dict.ResourceTypeEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDo;
import com.lhd.qd.module.sys.menu.model.factory.ResourceTreeVoFactory;
import com.lhd.qd.module.sys.menu.model.factory.RouterTreeVoFactory;
import com.lhd.qd.module.sys.role.dao.RoleResourceMapper;
import com.lhd.qd.module.sys.role.model.dto.RoleAssignResourceDto;
import com.lhd.qd.module.sys.role.model.entity.RoleResourceDo;
import com.lhd.qd.module.sys.role.model.factory.RoleCheckedMenuTreeVoFactory;
import com.lhd.qd.module.sys.role.model.factory.RoleResourceTreeVoFactory;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedPageElementVo;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedResourceVo;
import com.lhd.qd.module.sys.role.model.vo.RoleResourceVo;
import com.lhd.qd.module.sys.role.service.RoleResourceService;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 角色权限 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@Service
public class RoleResourceServiceImpl extends QdBaseServiceImpl<RoleResourceMapper, RoleResourceDo> implements RoleResourceService {

    @Override
    public List<AbstractTreeVo> getMenuTreeByRoleIdList(List<Long> roleIdList) {

        try {
            return TreeUtils.buildTree(RouterTreeVoFactory.class, 0L, baseMapper.selectMenuListByRoleIdList(roleIdList));
        } catch (Exception e) {
            throw new BusinessException("构建菜单树异常", e);
        }
    }

    @Override
    public Map<Long, String[]> getPageElementMapByRoleIdList(List<Long> roleIdList) {

        Map<Long, String[]> pageElementMap = new HashMap<>(16);

        List<PageElementDo> doList = baseMapper.selectPageElementGroupByRoleIdList(roleIdList);
        for (PageElementDo dataObj : doList) {

            if (StringUtils.isNotEmpty(dataObj.getElementCode())) {
                pageElementMap.put(dataObj.getMenuId(), dataObj.getElementCode().split(","));
            }
        }

        return pageElementMap;
    }

    @Override
    public List<String> getPermissionListByRoleIdList(List<Long> roleIdList) {

        return baseMapper.selectPermissionListByRoleIdList(CommonConsts.PERMISSION_SEPARATOR, roleIdList);
    }

    @Override
    public RoleResourceVo getResourceByRoleId(Long roleId) {

        List<AbstractTreeVo> pageElementList;
        try {
            pageElementList = TreeUtils.buildTree(RoleResourceTreeVoFactory.class, 0L,
                    baseMapper.selectPageElementAndMenuListByRoleId(roleId));
        } catch (Exception e) {
            throw new BusinessException("构建元素树异常", e);
        }


        List<AbstractTreeVo> menuList;
        try {
            menuList = TreeUtils.buildTree(ResourceTreeVoFactory.class, 0L,
                    baseMapper.selectMenuListByRoleIdList(Collections.singletonList(roleId)));
        } catch (Exception e) {
            throw new BusinessException("构建菜单树异常", e);
        }

        RoleResourceVo roleResourceVo = new RoleResourceVo();
        roleResourceVo.setMenuList(menuList);
        roleResourceVo.setPageElementList(pageElementList);

        return roleResourceVo;
    }

    @Override
    public RoleCheckedResourceVo getCheckedResourceByRoleId(Long roleId) {

        RoleCheckedResourceVo vo = new RoleCheckedResourceVo();
        vo.setMenuList(getCheckedMenuTreeByRoleId(roleId));
        vo.setPageElementMap(getPageElementCheckedListByRoleId(roleId));

        return vo;
    }

    @Override
    public void updateRoleResource(Long roleId, RoleAssignResourceDto dto) {

        remove(Wrappers.<RoleResourceDo>lambdaQuery().eq(RoleResourceDo::getRoleId, roleId));

        List<RoleResourceDo> doList = new ArrayList<>();

        List<Long> menuList = dto.getMenuList();
        if (menuList !=null && menuList.size() > 0) {
            menuList.forEach(item -> {
                RoleResourceDo dataObj = new RoleResourceDo();
                dataObj.setRoleId(roleId);
                dataObj.setResourceId(item);
                dataObj.setResourceType(ResourceTypeEnum.MENU.getValue());
                doList.add(dataObj);
            });
        }

        List<Long> pageElementList = dto.getPageElementList();
        if (pageElementList !=null && pageElementList.size() > 0) {
            pageElementList.forEach(item -> {
                RoleResourceDo dataObj = new RoleResourceDo();
                dataObj.setRoleId(roleId);
                dataObj.setResourceId(item);
                dataObj.setResourceType(ResourceTypeEnum.PAGE_ELEMENT.getValue());
                doList.add(dataObj);
            });
        }

        saveBatch(doList);
    }

    private List<AbstractTreeVo> getCheckedMenuTreeByRoleId(Long roleId) {

        try {
            return TreeUtils.buildTree(RoleCheckedMenuTreeVoFactory.class, 0L, baseMapper.selectCheckedMenuByRoleId(roleId));
        } catch (Exception e) {
            throw new BusinessException("构建角色选中菜单树异常", e);
        }
    }

    private Map<Long, List<RoleCheckedPageElementVo>> getPageElementCheckedListByRoleId(Long roleId) {

        Map<Long, List<RoleCheckedPageElementVo>> pageElementMap = new HashMap<>(16);

        List<RoleCheckedPageElementVo> voList = baseMapper.selectCheckedPageElementByRoleId(roleId);
        for (RoleCheckedPageElementVo vo : voList) {

            List<RoleCheckedPageElementVo> list = pageElementMap.computeIfAbsent(vo.getMenuId(), key -> new ArrayList<>());
            list.add(vo);
        }

        return pageElementMap;
    }
}
