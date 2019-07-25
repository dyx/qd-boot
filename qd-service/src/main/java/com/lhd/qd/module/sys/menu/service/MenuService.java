package com.lhd.qd.module.sys.menu.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDTO;
import com.lhd.qd.module.sys.menu.model.entity.MenuDO;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVO;

import java.util.List;

/**
 * <p>
 * 菜单 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
public interface MenuService extends QdBaseService<MenuDO> {

    /**
     * 获取菜单树
     * @return
     */
    List<AbstractTreeVO> getMenuTree();

    /**
     * 菜单详情
     * @param id
     * @return
     */
    MenuDetailVO getMenuById(Long id);

    /**
     * 新增菜单
     * @param saveDTO
     * @return
     */
    Long saveMenu(MenuSaveDTO saveDTO);

    /**
     * 修改菜单
     * @param id
     * @param saveDTO
     */
    void updateMenu(Long id, MenuSaveDTO saveDTO);

    /**
     * 删除菜单
     * @param id
     */
    void removeMenu(Long id);

}
