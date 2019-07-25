package com.lhd.qd.module.sys.menu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.menu.dao.PageElementMapper;
import com.lhd.qd.module.sys.menu.model.converter.AbstractPageElementConverter;
import com.lhd.qd.module.sys.menu.model.dto.PageElementPageQuery;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDTO;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDO;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVO;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVO;
import com.lhd.qd.module.sys.menu.service.PageElementService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面元素 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
@Service
public class PageElementServiceImpl extends QdBaseServiceImpl<PageElementMapper, PageElementDO> implements PageElementService {

    @Override
    public IPage<PageElementListVO> pagePageElementByMenuId(Long menuId, PageElementPageQuery query) {

        IPage<PageElementDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<PageElementDO>lambdaQuery().eq(PageElementDO::getMenuId, menuId));

        return AbstractPageElementConverter.INSTANCE.doPage2ListVOPage(doPage);
    }

    @Override
    public PageElementDetailVO getPageElementById(Long id) {

        PageElementDO dataObj = getById(id);

        return AbstractPageElementConverter.INSTANCE.do2DetailVO(dataObj);
    }

    @Override
    public void savePageElement(Long menuId, PageElementSaveDTO saveDTO) {

        vaild(null, menuId, saveDTO);

        PageElementDO dataObj = AbstractPageElementConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setMenuId(menuId);
        save(dataObj);
    }

    @Override
    public void updatePageElement(Long id, PageElementSaveDTO saveDTO) {

        Long menuId = getById(id).getMenuId();

        vaild(id, menuId, saveDTO);

        PageElementDO dataObj = AbstractPageElementConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removePageElement(Long id) {

        PageElementDO dataObj = new PageElementDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    private void vaild(Long id, Long menuId, PageElementSaveDTO saveDTO) {

        boolean isExist = count(Wrappers.<PageElementDO>lambdaQuery()
                .eq(PageElementDO::getMenuId, menuId)
                .eq(PageElementDO::getElementCode, saveDTO.getElementCode())
                .ne(id != null, PageElementDO::getId, id)) > 0;
        if (isExist) {
            throw new BusinessException("编码在该菜单下已存在，请更换");
        }

        isExist = count(Wrappers.<PageElementDO>lambdaQuery()
                .eq(PageElementDO::getMenuId, menuId)
                .eq(PageElementDO::getElementName, saveDTO.getElementName())
                .ne(id != null, PageElementDO::getId, id)) > 0;
        if (isExist) {
            throw new BusinessException("名称在该菜单下已存在，请更换");
        }
    }
}
