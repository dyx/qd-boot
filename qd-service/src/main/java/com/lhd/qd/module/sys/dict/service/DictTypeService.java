package com.lhd.qd.module.sys.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.dict.model.dto.DictTypePageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDTO;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDO;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVO;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVO;

/**
 * <p>
 * 字典类型 服务
 * </p>
 *
 * @author lhd
 * @since 2019-06-01
 */
public interface DictTypeService extends QdBaseService<DictTypeDO> {

    /**
     * 字典类型分页列表
     * @param query
     * @return
     */
    IPage<DictTypeListVO> pageDictType(DictTypePageQuery query);

    /**
     * 字典类型详情
     * @param id
     * @return
     */
    DictTypeDetailVO getDictTypeById(Long id);

    /**
     * 编码是否存在
     * @param typeCode
     * @return
     */
    Boolean isExistByCode(String typeCode);

    /**
     * 新增字典类型
     * @param saveDTO
     */
    void saveDictType(DictTypeSaveDTO saveDTO);

    /**
     * 修改字典类型
     * @param id
     * @param saveDTO
     */
    void updateDictType(Long id, DictTypeSaveDTO saveDTO);

    /**
     * 删除字典类型
     * @param id
     */
    void removeDictType(Long id);
}
