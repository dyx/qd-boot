package com.lhd.qd.module.sys.menu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.menu.model.dto.PageElementPageQuery;
import com.lhd.qd.module.sys.menu.model.dto.PageElementSaveDTO;
import com.lhd.qd.module.sys.menu.model.entity.PageElementDO;
import com.lhd.qd.module.sys.menu.model.vo.PageElementDetailVO;
import com.lhd.qd.module.sys.menu.model.vo.PageElementListVO;

/**
 * <p>
 * 页面元素 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-25
 */
public interface PageElementService extends QdBaseService<PageElementDO> {

    /**
     * 页面元素分页列表
     * @param menuId
     * @param query
     * @return
     */
    IPage<PageElementListVO> pagePageElementByMenuId(Long menuId, PageElementPageQuery query);

    /**
     * 页面元素详情
     * @param id
     * @return
     */
    PageElementDetailVO getPageElementById(Long id);

    /**
     * 新增页面元素
     * @param menuId
     * @param saveDTO
     */
    void savePageElement(Long menuId, PageElementSaveDTO saveDTO);

    /**
     * 修改页面元素
     * @param id
     * @param saveDTO
     */
    void updatePageElement(Long id, PageElementSaveDTO saveDTO);

    /**
     * 删除页面元素
     * @param id
     */
    void removePageElement(Long id);
}
