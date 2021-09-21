package com.lhd.qd.module.sys.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.dict.dao.DictMapper;
import com.lhd.qd.module.sys.dict.model.converter.AbstractDictConverter;
import com.lhd.qd.module.sys.dict.model.dto.DictPageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDto;
import com.lhd.qd.module.sys.dict.model.entity.DictDo;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictListVo;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVo;
import com.lhd.qd.module.sys.dict.service.DictService;
import com.lhd.qd.util.RedisUtils;
import com.lhd.qd.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-31
 */
@Service
public class DictServiceImpl extends QdBaseServiceImpl<DictMapper, DictDo> implements DictService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private DictTypeServiceImpl typeService;

    @Override
    public IPage<DictListVo> pageDict(DictPageQuery query) {

        IPage<DictDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<DictDo>lambdaQuery());

        return AbstractDictConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @Override
    public IPage<DictListVo> pageDictByTypeCode(String typeCode, DictPageQuery query) {

        @SuppressWarnings("unchecked")
        IPage<DictDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<DictDo>lambdaQuery()
                        .eq(DictDo::getTypeCode, typeCode)
                        .orderBy(isOrderBy(query, "sort_number"), isAsc(query), DictDo::getSortNum));

        return AbstractDictConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DictPageBindVo> listDictCacheByTypeCode(String typeCode) {
        return (List<DictPageBindVo>) redisUtils.getHash(RedisConsts.DICT_KEY, typeCode);
    }

    @Override
    public DictDetailVo getDictById(Long id) {

        DictDo dataObj = getById(id);

        return AbstractDictConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void saveDict(DictSaveDto saveDto) {

        valid(null, saveDto);

        DictDo dataObj = AbstractDictConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);

        updateDictCache(saveDto.getTypeCode());
    }

    @Override
    public void updateDict(Long id, DictSaveDto saveDto) {

        valid(id, saveDto);

        DictDo dataObj = AbstractDictConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);

        updateDictCache(saveDto.getTypeCode());
    }

    @Override
    public void updateDictCacheWithValid(String typeCode) {

        if (!typeService.isExistByCode(typeCode)) {
            throw new BusinessException("分类编码不存在，请更换");
        }

        updateDictCache(typeCode);
    }

    @Override
    public void updateDictCache(String typeCode) {

        List<DictDo> doList =list(Wrappers.<DictDo>lambdaQuery().eq(DictDo::getTypeCode, typeCode).orderByAsc(DictDo::getSortNum));

        redisUtils.setHash(RedisConsts.DICT_KEY, typeCode, AbstractDictConverter.INSTANCE.doList2PageBindVoList(doList));
    }

    @Override
    public void removeDict(Long id) {

        String typeCode = getById(id).getTypeCode();

        DictDo dataObj = new DictDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);

        updateDictCache(typeCode);
    }

    @Override
    public void removeDictByTypeCode(String typeCode) {

        DictDo dataObj = new DictDo();
        dataObj.setUpdateTime(LocalDateTime.now());
        dataObj.setUpdateUserId(UserUtils.getUserId());
        dataObj.setDeleted(true);
        update(dataObj, Wrappers.<DictDo>lambdaQuery().eq(DictDo::getTypeCode, typeCode));

        removeDictCache(typeCode);
    }

    @Override
    public Map<String, List<DictPageBindVo>> getPageCacheMap() {

        @SuppressWarnings("unchecked")
        List<DictDo> doList = list(Wrappers.<DictDo>lambdaQuery().orderByAsc(DictDo::getTypeCode, DictDo::getSortNum));

        Map<String, List<DictPageBindVo>> dictMap = new HashMap<>(16);
        for (DictDo dataObj : doList) {

            // 显示列表
            List<DictPageBindVo> voList = dictMap.computeIfAbsent(dataObj.getTypeCode(), key -> new ArrayList<>());
            voList.add(AbstractDictConverter.INSTANCE.do2PageBindVo(dataObj));
        }

        return dictMap;
    }

    private void valid(Long id, DictSaveDto saveDto) {

        if (!typeService.isExistByCode(saveDto.getTypeCode())) {
            throw new BusinessException("分类编码不存在，请更换");
        }

        boolean isExistValue = count(Wrappers.<DictDo>lambdaQuery()
                .eq(DictDo::getTypeCode, saveDto.getTypeCode())
                .eq(DictDo::getDictValue, saveDto.getDictValue())
                .ne(id != null, DictDo::getId, id)) > 0;
        if (isExistValue) {
            throw new BusinessException("值在该分类下已存在，请更换");
        }

        boolean isExistDesc = count(Wrappers.<DictDo>lambdaQuery()
                .eq(DictDo::getTypeCode, saveDto.getTypeCode())
                .eq(DictDo::getDictDesc, saveDto.getDictDesc())
                .ne(id != null, DictDo::getId, id)) > 0;
        if (isExistDesc) {
            throw new BusinessException("描述在该分类下已存在，请更换");
        }
    }

    private void removeDictCache(String typeCode) {

        redisUtils.delHash(RedisConsts.DICT_KEY, typeCode);
    }
}
