package com.lhd.qd.module.sys.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.dict.model.dto.DictPageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDTO;
import com.lhd.qd.module.sys.dict.model.entity.DictDO;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVO;
import com.lhd.qd.module.sys.dict.model.vo.DictListVO;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 服务
 * </p>
 *
 * @author lhd
 * @since 2019-05-31
 */
public interface DictService extends QdBaseService<DictDO> {

    /**
     * 字典分页列表
     * @param query
     * @return
     */
    IPage<DictListVO> pageDict(DictPageQuery query);

    /**
     * 根据类型编码获取字典
     * @param typeCode
     * @param query
     * @return
     */
    IPage<DictListVO> pageDictByTypeCode(String typeCode, DictPageQuery query);

    /**
     * 根据类型编码获取字典缓存
     * @param typeCode
     * @return
     */
    List<DictPageBindVO> listDictCacheByTypeCode(String typeCode);

    /**
     * 字典详情
     * @param id
     * @return
     */
    DictDetailVO getDictById(Long id);

    /**
     * 新增字典
     * @param saveDTO
     */
    void saveDict(DictSaveDTO saveDTO);

    /**
     * 修改字典
     * @param id
     * @param saveDTO
     */
    void updateDict(Long id, DictSaveDTO saveDTO);

    /**
     * 根据编码修改字典缓存，并校验编码
     * @param typeCode
     */
    void updateDictCacheWithValid(String typeCode);

    /**
     * 根据编码修改字典缓存
     * @param typeCode
     */
    void updateDictCache(String typeCode);

    /**
     * 删除字典
     * @param id
     */
    void removeDict(Long id);

    /**
     * 根据编码删除字典
     * @param typeCode
     */
    void removeDictByTypeCode(String typeCode);

    /**
     * 前端缓存映射
     * 用于前端字段翻译、控件绑定
     * @return
     */
    Map<String, List<DictPageBindVO>> getPageCacheMap();
}
