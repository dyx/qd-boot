package com.lhd.qd.module.sale.custom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sale.custom.model.dto.CustomPageQuery;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDto;
import com.lhd.qd.module.sale.custom.model.entity.CustomDo;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVo;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVo;

import java.util.List;

/**
 * <p>
 * 客户 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
public interface CustomService extends QdBaseService<CustomDo> {

    /**
     * 客户分页列表
     * @param query
     * @return
     */
    IPage<CustomListVo> pageCustom(CustomPageQuery query);

    /**
     * 客户详情
     * @param id
     * @return
     */
    CustomDetailVo getCustomById(Long id);

    /**
     * 新增客户
     * @param saveDto
     */
    void saveCustom(CustomSaveDto saveDto);

    /**
     * 修改客户
     * @param id
     * @param saveDto
     */
    void updateCustom(Long id, CustomSaveDto saveDto);

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
