package com.lhd.qd.module.sys.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.dict.model.dto.DictTypePageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDto;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVo;

/**
 * <p>
 * 字典类型 服务
 * </p>
 *
 * @author lhd
 * @since 2019-06-01
 */
public interface DictTypeService extends QdBaseService<DictTypeDo> {

    /**
     * 字典类型分页列表
     * @param query
     * @return
     */
    IPage<DictTypeListVo> pageDictType(DictTypePageQuery query);

    /**
     * 字典类型详情
     * @param id
     * @return
     */
    DictTypeDetailVo getDictTypeById(Long id);

    /**
     * 编码是否存在
     * @param typeCode
     * @return
     */
    Boolean isExistByCode(String typeCode);

    /**
     * 新增字典类型
     * @param saveDto
     */
    void saveDictType(DictTypeSaveDto saveDto);

    /**
     * 修改字典类型
     * @param id
     * @param saveDto
     */
    void updateDictType(Long id, DictTypeSaveDto saveDto);

    /**
     * 删除字典类型
     * @param id
     */
    void removeDictType(Long id);
}
