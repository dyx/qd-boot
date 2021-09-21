package com.lhd.qd.module.sys.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.dict.model.dto.DictPageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDto;
import com.lhd.qd.module.sys.dict.model.entity.DictDo;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictListVo;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVo;

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
public interface DictService extends QdBaseService<DictDo> {

    /**
     * 字典分页列表
     * @param query
     * @return
     */
    IPage<DictListVo> pageDict(DictPageQuery query);

    /**
     * 根据类型编码获取字典
     * @param typeCode
     * @param query
     * @return
     */
    IPage<DictListVo> pageDictByTypeCode(String typeCode, DictPageQuery query);

    /**
     * 根据类型编码获取字典缓存
     * @param typeCode
     * @return
     */
    List<DictPageBindVo> listDictCacheByTypeCode(String typeCode);

    /**
     * 字典详情
     * @param id
     * @return
     */
    DictDetailVo getDictById(Long id);

    /**
     * 新增字典
     * @param saveDto
     */
    void saveDict(DictSaveDto saveDto);

    /**
     * 修改字典
     * @param id
     * @param saveDto
     */
    void updateDict(Long id, DictSaveDto saveDto);

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
    Map<String, List<DictPageBindVo>> getPageCacheMap();
}
