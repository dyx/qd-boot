package com.lhd.qd.module.sys.menu.service.impl;

import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.menu.dao.MenuMapper;
import com.lhd.qd.module.sys.menu.model.converter.MenuConverter;
import com.lhd.qd.module.sys.menu.model.dto.MenuSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.MenuDo;
import com.lhd.qd.module.sys.menu.model.factory.ResourceTreeVoFactory;
import com.lhd.qd.module.sys.menu.model.vo.MenuDetailVo;
import com.lhd.qd.module.sys.menu.service.MenuService;
import com.lhd.qd.trans.annotation.RefTrans;
import com.lhd.qd.trans.annotation.RefTranslating;
import com.lhd.qd.trans.consts.RefTransType;
import com.lhd.qd.tree.AbstractTreeVo;
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
public class MenuServiceImpl extends QdBaseServiceImpl<MenuMapper, MenuDo> implements MenuService {

    @Override
    public List<AbstractTreeVo> getMenuTree() {

        List<MenuDo> doList = list(null);
        try {
            return TreeUtils.buildTree(ResourceTreeVoFactory.class, 0L, doList);
        } catch (Exception e) {
            throw new BusinessException("构建资源树异常", e);
        }
    }

    @RefTranslating({
            @RefTrans(type = RefTransType.USER, readFieldName = "createUserId", writeFieldNames = "createUserName"),
            @RefTrans(type = RefTransType.USER, readFieldName = "updateUserId", writeFieldNames = "updateUserName")
    })
    @Override
    public MenuDetailVo getMenuById(Long id) {

        MenuDo dataObj = getById(id);

        return MenuConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public Long saveMenu(MenuSaveDto saveDto) {

        MenuDo dataObj = MenuConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);

        return dataObj.getId();
    }

    @Override
    public void updateMenu(Long id, MenuSaveDto saveDto) {

        MenuDo dataObj = MenuConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeMenu(Long id) {

        MenuDo dataObj = new MenuDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }
}
