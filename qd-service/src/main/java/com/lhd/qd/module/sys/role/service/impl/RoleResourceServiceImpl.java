package com.lhd.qd.module.sys.role.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.constant.dict.ResourceTypeEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDO;
import com.lhd.qd.module.sys.menu.model.factory.ResourceTreeVoFactory;
import com.lhd.qd.module.sys.menu.model.factory.RouterTreeVoFactory;
import com.lhd.qd.module.sys.role.dao.RoleResourceMapper;
import com.lhd.qd.module.sys.role.model.dto.RoleAssignResourceDTO;
import com.lhd.qd.module.sys.role.model.entity.RoleResourceDO;
import com.lhd.qd.module.sys.role.model.factory.RoleCheckedMenuTreeVoFactory;
import com.lhd.qd.module.sys.role.model.factory.RoleResourceTreeVoFactory;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedPageElementVO;
import com.lhd.qd.module.sys.role.model.vo.RoleCheckedResourceVO;
import com.lhd.qd.module.sys.role.model.vo.RoleResourceVO;
import com.lhd.qd.module.sys.role.service.RoleResourceService;
import com.lhd.qd.tree.AbstractTreeVO;
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
public class RoleResourceServiceImpl extends QdBaseServiceImpl<RoleResourceMapper, RoleResourceDO> implements RoleResourceService {

    @Override
    public List<AbstractTreeVO> getMenuTreeByRoleIdList(List<Long> roleIdList) {

        try {
            return TreeUtils.buildTree(RouterTreeVoFactory.class, 0L, baseMapper.selectMenuListByRoleIdList(roleIdList));
        } catch (Exception e) {
            throw new BusinessException("构建菜单树异常", e);
        }
    }

    @Override
    public Map<Long, String[]> getPageElementMapByRoleIdList(List<Long> roleIdList) {

        Map<Long, String[]> pageElementMap = new HashMap<>(16);

        List<PageElementDO> doList = baseMapper.selectPageElementGroupByRoleIdList(roleIdList);
        for (PageElementDO dataObj : doList) {

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
    public RoleResourceVO getResourceByRoleId(Long roleId) {

        List<AbstractTreeVO> pageElementList;
        try {
            pageElementList = TreeUtils.buildTree(RoleResourceTreeVoFactory.class, 0L,
                    baseMapper.selectPageElementAndMenuListByRoleId(roleId));
        } catch (Exception e) {
            throw new BusinessException("构建元素树异常", e);
        }


        List<AbstractTreeVO> menuList;
        try {
            menuList = TreeUtils.buildTree(ResourceTreeVoFactory.class, 0L,
                    baseMapper.selectMenuListByRoleIdList(Collections.singletonList(roleId)));
        } catch (Exception e) {
            throw new BusinessException("构建菜单树异常", e);
        }

        RoleResourceVO roleResourceVO = new RoleResourceVO();
        roleResourceVO.setMenuList(menuList);
        roleResourceVO.setPageElementList(pageElementList);

        return roleResourceVO;
    }

    @Override
    public RoleCheckedResourceVO getCheckedResourceByRoleId(Long roleId) {

        RoleCheckedResourceVO vo = new RoleCheckedResourceVO();
        vo.setMenuList(getCheckedMenuTreeByRoleId(roleId));
        vo.setPageElementMap(getPageElementCheckedListByRoleId(roleId));

        return vo;
    }

    @Override
    public void updateRoleResource(Long roleId, RoleAssignResourceDTO dto) {

        remove(Wrappers.<RoleResourceDO>lambdaQuery().eq(RoleResourceDO::getRoleId, roleId));

        List<RoleResourceDO> doList = new ArrayList<>();

        List<Long> menuList = dto.getMenuList();
        if (menuList !=null && menuList.size() > 0) {
            menuList.forEach(item -> {
                RoleResourceDO dataObj = new RoleResourceDO();
                dataObj.setRoleId(roleId);
                dataObj.setResourceId(item);
                dataObj.setResourceType(ResourceTypeEnum.MENU.getValue());
                doList.add(dataObj);
            });
        }

        List<Long> pageElementList = dto.getPageElementList();
        if (pageElementList !=null && pageElementList.size() > 0) {
            pageElementList.forEach(item -> {
                RoleResourceDO dataObj = new RoleResourceDO();
                dataObj.setRoleId(roleId);
                dataObj.setResourceId(item);
                dataObj.setResourceType(ResourceTypeEnum.PAGE_ELEMENT.getValue());
                doList.add(dataObj);
            });
        }

        saveBatch(doList);
    }

    private List<AbstractTreeVO> getCheckedMenuTreeByRoleId(Long roleId) {

        try {
            return TreeUtils.buildTree(RoleCheckedMenuTreeVoFactory.class, 0L, baseMapper.selectCheckedMenuByRoleId(roleId));
        } catch (Exception e) {
            throw new BusinessException("构建角色选中菜单树异常", e);
        }
    }

    private Map<Long, List<RoleCheckedPageElementVO>> getPageElementCheckedListByRoleId(Long roleId) {

        Map<Long, List<RoleCheckedPageElementVO>> pageElementMap = new HashMap<>(16);

        List<RoleCheckedPageElementVO> voList = baseMapper.selectCheckedPageElementByRoleId(roleId);
        for (RoleCheckedPageElementVO vo : voList) {

            List<RoleCheckedPageElementVO> list = pageElementMap.computeIfAbsent(vo.getMenuId(), key -> new ArrayList<>());
            list.add(vo);
        }

        return pageElementMap;
    }
}
