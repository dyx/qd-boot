package com.lhd.qd.module.sys.menu.service.impl;

import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.menu.dao.MenuMapper;
import com.lhd.qd.module.sys.menu.model.converter.AbstractMenuConverter;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDTO;
import com.lhd.qd.module.sys.menu.model.entity.MenuDO;
import com.lhd.qd.tree.AbstractTreeVO;
import com.lhd.qd.module.sys.menu.model.factory.ResourceTreeVoFactory;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVO;
import com.lhd.qd.module.sys.menu.service.MenuService;
import com.lhd.qd.util.TreeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@Service
public class MenuServiceImpl extends QdBaseServiceImpl<MenuMapper, MenuDO> implements MenuService {

    @Override
    public List<AbstractTreeVO> getMenuTree() {

        List<MenuDO> doList = list(null);
        try {
            return TreeUtils.buildTree(ResourceTreeVoFactory.class, 0L, doList);
        } catch (Exception e) {
            throw new BusinessException("构建资源树异常", e);
        }
    }

    @Override
    public MenuDetailVO getMenuById(Long id) {

        MenuDO dataObj = getById(id);

        return AbstractMenuConverter.INSTANCE.do2DetailVO(dataObj);
    }

    @Override
    public Long saveMenu(MenuSaveDTO saveDTO) {

        MenuDO dataObj = AbstractMenuConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);

        return dataObj.getId();
    }

    @Override
    public void updateMenu(Long id, MenuSaveDTO saveDTO) {

        MenuDO dataObj = AbstractMenuConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeMenu(Long id) {

        MenuDO dataObj = new MenuDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }
}
