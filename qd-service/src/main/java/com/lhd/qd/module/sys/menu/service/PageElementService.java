package com.lhd.qd.module.sys.menu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.menu.model.dto.PageElementPageQuery;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDto;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVo;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVo;

/**
 * <p>
 * 页面元素 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
public interface PageElementService extends QdBaseService<PageElementDo> {

    /**
     * 页面元素分页列表
     * @param menuId
     * @param query
     * @return
     */
    IPage<PageElementListVo> pagePageElementByMenuId(Long menuId, PageElementPageQuery query);

    /**
     * 页面元素详情
     * @param id
     * @return
     */
    PageElementDetailVo getPageElementById(Long id);

    /**
     * 新增页面元素
     * @param menuId
     * @param saveDto
     */
    void savePageElement(Long menuId, PageElementSaveDto saveDto);

    /**
     * 修改页面元素
     * @param id
     * @param saveDto
     */
    void updatePageElement(Long id, PageElementSaveDto saveDto);

    /**
     * 删除页面元素
     * @param id
     */
    void removePageElement(Long id);
}
