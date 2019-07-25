package com.lhd.qd.module.sys.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseDO;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.dict.dao.DictMapper;
import com.lhd.qd.module.sys.dict.model.converter.AbstractDictConverter;
import com.lhd.qd.module.sys.dict.model.dto.DictPageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDTO;
import com.lhd.qd.module.sys.dict.model.entity.DictDO;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVO;
import com.lhd.qd.module.sys.dict.model.vo.DictListVO;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVO;
import com.lhd.qd.module.sys.dict.service.DictService;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.service.TransService;
import com.lhd.qd.module.sys.trans.util.SysTransDtoUtils;
import com.lhd.qd.util.RedisUtils;
import com.lhd.qd.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 字典 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-05-31
 */
@Service
public class DictServiceImpl extends QdBaseServiceImpl<DictMapper, DictDO> implements DictService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TransService transService;

    @Autowired
    private DictTypeServiceImpl typeService;

    @Override
    public IPage<DictListVO> pageDict(DictPageQuery query) {

        IPage<DictDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<DictDO>lambdaQuery());

        return AbstractDictConverter.INSTANCE.doPage2ListVOPage(doPage, getTransVO(doPage.getRecords()));
    }

    @Override
    public IPage<DictListVO> pageDictByTypeCode(String typeCode, DictPageQuery query) {

        @SuppressWarnings("unchecked")
        IPage<DictDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<DictDO>lambdaQuery()
                        .eq(DictDO::getTypeCode, typeCode)
                        .orderBy(isOrderBy(query, DictDO::getSortNum), isAsc(query), DictDO::getSortNum));

        return AbstractDictConverter.INSTANCE.doPage2ListVOPage(doPage, getTransVO(doPage.getRecords()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DictPageBindVO> listDictCacheByTypeCode(String typeCode) {
        return (List<DictPageBindVO>) redisUtils.getHash(RedisConsts.DICT_KEY, typeCode);
    }

    @Override
    public DictDetailVO getDictById(Long id) {

        DictDO dataObj = getById(id);

        return AbstractDictConverter.INSTANCE.do2DetailVO(dataObj, getTransVO(Collections.singletonList(dataObj)));
    }

    @Override
    public void saveDict(DictSaveDTO saveDTO) {

        vaild(null, saveDTO);

        DictDO dataObj = AbstractDictConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);

        updateDictCache(saveDTO.getTypeCode());
    }

    @Override
    public void updateDict(Long id, DictSaveDTO saveDTO) {

        vaild(id, saveDTO);

        DictDO dataObj = AbstractDictConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);

        updateDictCache(saveDTO.getTypeCode());
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

        List<DictDO> doList =list(Wrappers.<DictDO>lambdaQuery().eq(DictDO::getTypeCode, typeCode).orderByAsc(DictDO::getSortNum));

        redisUtils.setHash(RedisConsts.DICT_KEY, typeCode, AbstractDictConverter.INSTANCE.doList2PageBindVOList(doList));
    }

    @Override
    public void removeDict(Long id) {

        String typeCode = getById(id).getTypeCode();

        DictDO dataObj = new DictDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);

        updateDictCache(typeCode);
    }

    @Override
    public void removeDictByTypeCode(String typeCode) {

        DictDO dataObj = new DictDO();
        dataObj.setUpdateTime(LocalDateTime.now());
        dataObj.setUpdateUserId(UserUtils.getUserId());
        dataObj.setDeleted(true);
        update(dataObj, Wrappers.<DictDO>lambdaQuery().eq(DictDO::getTypeCode, typeCode));

        removeDictCache(typeCode);
    }

    @Override
    public Map<String, List<DictPageBindVO>> getPageCacheMap() {

        @SuppressWarnings("unchecked")
        List<DictDO> doList = list(Wrappers.<DictDO>lambdaQuery().orderByAsc(DictDO::getTypeCode, DictDO::getSortNum));

        Map<String, List<DictPageBindVO>> dictMap = new HashMap<>(16);
        for (DictDO dataObj : doList) {

            // 显示列表
            List<DictPageBindVO> voList = dictMap.computeIfAbsent(dataObj.getTypeCode(), key -> new ArrayList<>());
            voList.add(AbstractDictConverter.INSTANCE.do2PageBindVO(dataObj));
        }

        return dictMap;
    }

    private void vaild(Long id, DictSaveDTO saveDTO) {

        if (!typeService.isExistByCode(saveDTO.getTypeCode())) {
            throw new BusinessException("分类编码不存在，请更换");
        }

        boolean isExistValue = count(Wrappers.<DictDO>lambdaQuery()
                .eq(DictDO::getTypeCode, saveDTO.getTypeCode())
                .eq(DictDO::getDictValue, saveDTO.getDictValue())
                .ne(id != null, DictDO::getId, id)) > 0;
        if (isExistValue) {
            throw new BusinessException("值在该分类下已存在，请更换");
        }

        boolean isExistDesc = count(Wrappers.<DictDO>lambdaQuery()
                .eq(DictDO::getTypeCode, saveDTO.getTypeCode())
                .eq(DictDO::getDictDesc, saveDTO.getDictDesc())
                .ne(id != null, DictDO::getId, id)) > 0;
        if (isExistDesc) {
            throw new BusinessException("描述在该分类下已存在，请更换");
        }
    }

    private List<TransVO> getTransVO(List<DictDO> doList) {

        List<TransDTO> dtoList = new ArrayList<>();
        Set<String> codeList = doList.stream().map(DictDO::getTypeCode).collect(toSet());
        dtoList.add(SysTransDtoUtils.transDictTypeDescByCode(codeList, DictDO::getTypeCode, DictListVO::getTypeName));

        dtoList.add(SysTransDtoUtils.transCreateUser(doList.stream().map(QdBaseDO::getCreateUserId).collect(toSet())));
        dtoList.add(SysTransDtoUtils.transUpdateUser(doList.stream().map(QdBaseDO::getUpdateUserId).collect(toSet())));

        return transService.getTransValue(dtoList);
    }

    private void removeDictCache(String typeCode) {

        redisUtils.delHash(RedisConsts.DICT_KEY, typeCode);
    }
}
