package com.lhd.qd.module.sys.menu.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.MenuDo;
import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVo;

import java.util.List;

/**
 * <p>
 * 菜单 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
public interface MenuService extends QdBaseService<MenuDo> {

    /**
     * 获取菜单树
     * @return
     */
    List<AbstractTreeVo> getMenuTree();

    /**
     * 菜单详情
     * @param id
     * @return
     */
    MenuDetailVo getMenuById(Long id);

    /**
     * 新增菜单
     * @param saveDto
     * @return
     */
    Long saveMenu(MenuSaveDto saveDto);

    /**
     * 修改菜单
     * @param id
     * @param saveDto
     */
    void updateMenu(Long id, MenuSaveDto saveDto);

    /**
     * 删除菜单
     * @param id
     */
    void removeMenu(Long id);

}
