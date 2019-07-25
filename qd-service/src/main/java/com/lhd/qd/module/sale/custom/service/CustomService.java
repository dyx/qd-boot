package com.lhd.qd.module.sale.custom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sale.custom.model.dto.CustomPageQuery;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDTO;
import com.lhd.qd.module.sale.custom.model.entity.CustomDO;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVO;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVO;

import java.util.List;

/**
 * <p>
 * 客户 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
public interface CustomService extends QdBaseService<CustomDO> {

    /**
     * 客户分页列表
     * @param query
     * @return
     */
    IPage<CustomListVO> pageCustom(CustomPageQuery query);

    /**
     * 客户详情
     * @param id
     * @return
     */
    CustomDetailVO getCustomById(Long id);

    /**
     * 新增客户
     * @param saveDTO
     */
    void saveCustom(CustomSaveDTO saveDTO);

    /**
     * 修改客户
     * @param id
     * @param saveDTO
     */
    void updateCustom(Long id, CustomSaveDTO saveDTO);

    /**
     * 删除客户
     * @param id
     */
    void removeCustom(Long id);

    /**
     * 批量删除客户
     * @param idList
     */
    void batchRemoveCustom(List<Long> idList);
}
