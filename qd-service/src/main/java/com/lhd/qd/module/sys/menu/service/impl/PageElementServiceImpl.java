package com.lhd.qd.module.sys.menu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.menu.dao.PageElementMapper;
import com.lhd.qd.module.sys.menu.model.converter.AbstractPageElementConverter;
import com.lhd.qd.module.sys.menu.model.dto.PageElementPageQuery;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVo;
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
public class PageElementServiceImpl extends QdBaseServiceImpl<PageElementMapper, PageElementDo> implements PageElementService {

    @Override
    public IPage<PageElementListVo> pagePageElementByMenuId(Long menuId, PageElementPageQuery query) {

        IPage<PageElementDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<PageElementDo>lambdaQuery().eq(PageElementDo::getMenuId, menuId));

        return AbstractPageElementConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @Override
    public PageElementDetailVo getPageElementById(Long id) {

        PageElementDo dataObj = getById(id);

        return AbstractPageElementConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void savePageElement(Long menuId, PageElementSaveDto saveDto) {

        valid(null, menuId, saveDto);

        PageElementDo dataObj = AbstractPageElementConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setMenuId(menuId);
        save(dataObj);
    }

    @Override
    public void updatePageElement(Long id, PageElementSaveDto saveDto) {

        Long menuId = getById(id).getMenuId();

        valid(id, menuId, saveDto);

        PageElementDo dataObj = AbstractPageElementConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removePageElement(Long id) {

        PageElementDo dataObj = new PageElementDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    private void valid(Long id, Long menuId, PageElementSaveDto saveDto) {

        boolean isExist = count(Wrappers.<PageElementDo>lambdaQuery()
                .eq(PageElementDo::getMenuId, menuId)
                .eq(PageElementDo::getElementCode, saveDto.getElementCode())
                .ne(id != null, PageElementDo::getId, id)) > 0;
        if (isExist) {
            throw new BusinessException("编码在该菜单下已存在，请更换");
        }

        isExist = count(Wrappers.<PageElementDo>lambdaQuery()
                .eq(PageElementDo::getMenuId, menuId)
                .eq(PageElementDo::getElementName, saveDto.getElementName())
                .ne(id != null, PageElementDo::getId, id)) > 0;
        if (isExist) {
            throw new BusinessException("名称在该菜单下已存在，请更换");
        }
    }
}
