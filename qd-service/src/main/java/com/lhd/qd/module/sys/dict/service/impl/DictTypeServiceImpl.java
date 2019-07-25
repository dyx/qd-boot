package com.lhd.qd.module.sys.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseDO;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.dict.dao.DictTypeMapper;
import com.lhd.qd.module.sys.dict.model.converter.AbstractDictTypeConverter;
import com.lhd.qd.module.sys.dict.model.dto.DictTypePageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDTO;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDO;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVO;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVO;
import com.lhd.qd.module.sys.dict.service.DictService;
import com.lhd.qd.module.sys.dict.service.DictTypeService;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.service.TransService;
import com.lhd.qd.module.sys.trans.util.SysTransDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 字典类型 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-06-01
 */
@Service
public class DictTypeServiceImpl extends QdBaseServiceImpl<DictTypeMapper, DictTypeDO> implements DictTypeService {

    @Autowired
    private DictService dictService;

    @Autowired
    private TransService transService;

    @Override
    public IPage<DictTypeListVO> pageDictType(DictTypePageQuery query) {

        IPage<DictTypeDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<DictTypeDO>lambdaQuery()
                        .like(StringUtils.isNotEmpty(query.getTypeCode()), DictTypeDO::getTypeCode, query.getTypeCode())
                        .like(StringUtils.isNotEmpty(query.getTypeDesc()), DictTypeDO::getTypeDesc, query.getTypeDesc()));

        return AbstractDictTypeConverter.INSTANCE.doPage2ListVOPage(doPage);
    }

    @Override
    public DictTypeDetailVO getDictTypeById(Long id) {

        DictTypeDO dataObj = getById(id);

        return AbstractDictTypeConverter.INSTANCE.do2DetailVO(dataObj, getTransVO(Collections.singletonList(dataObj)));
    }

    @Override
    public Boolean isExistByCode(String typeCode) {
        return count(Wrappers.<DictTypeDO>lambdaQuery().eq(DictTypeDO::getTypeCode, typeCode)) > 0;
    }

    @Override
    public void saveDictType(DictTypeSaveDTO saveDTO) {

        vaild(null, saveDTO);

        DictTypeDO dataObj = AbstractDictTypeConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);
    }

    @Override
    public void updateDictType(Long id, DictTypeSaveDTO saveDTO) {

        vaild(id, saveDTO);

        DictTypeDO dataObj = AbstractDictTypeConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);

        dictService.updateDictCache(saveDTO.getTypeCode());
    }

    @Override
    public void removeDictType(Long id) {

        String typeCode = getById(id).getTypeCode();

        DictTypeDO dataObj = new DictTypeDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);

        dictService.removeDictByTypeCode(typeCode);
    }

    private void vaild(Long id, DictTypeSaveDTO saveDTO) {

        Integer count = count(Wrappers.<DictTypeDO>lambdaQuery()
                .eq(DictTypeDO::getTypeCode, saveDTO.getTypeCode())
                .ne(id != null, DictTypeDO::getId, id));
        if (count > 0) {
            throw new BusinessException("编码已存在，请更换。");
        }
    }

    private List<TransVO> getTransVO(List<DictTypeDO> doList) {

        List<TransDTO> dtoList = new ArrayList<>();
        dtoList.add(SysTransDtoUtils.transCreateUser(doList.stream().map(QdBaseDO::getCreateUserId).collect(toSet())));
        dtoList.add(SysTransDtoUtils.transUpdateUser(doList.stream().map(QdBaseDO::getUpdateUserId).collect(toSet())));

        return transService.getTransValue(dtoList);
    }
}
