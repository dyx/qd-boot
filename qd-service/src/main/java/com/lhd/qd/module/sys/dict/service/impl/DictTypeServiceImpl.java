package com.lhd.qd.module.sys.dict.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.dict.dao.DictTypeMapper;
import com.lhd.qd.module.sys.dict.model.converter.AbstractDictTypeConverter;
import com.lhd.qd.module.sys.dict.model.dto.DictTypePageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDto;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVo;
import com.lhd.qd.module.sys.dict.service.DictService;
import com.lhd.qd.module.sys.dict.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-06-01
 */
@Service
public class DictTypeServiceImpl extends QdBaseServiceImpl<DictTypeMapper, DictTypeDo> implements DictTypeService {

    @Autowired
    private DictService dictService;

    @Override
    public IPage<DictTypeListVo> pageDictType(DictTypePageQuery query) {

        IPage<DictTypeDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<DictTypeDo>lambdaQuery()
                        .like(StrUtil.isNotEmpty(query.getTypeCode()), DictTypeDo::getTypeCode, query.getTypeCode())
                        .like(StrUtil.isNotEmpty(query.getTypeDesc()), DictTypeDo::getTypeDesc, query.getTypeDesc()));

        return AbstractDictTypeConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @Override
    public DictTypeDetailVo getDictTypeById(Long id) {

        DictTypeDo dataObj = getById(id);

        return AbstractDictTypeConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public Boolean isExistByCode(String typeCode) {
        return count(Wrappers.<DictTypeDo>lambdaQuery().eq(DictTypeDo::getTypeCode, typeCode)) > 0;
    }

    @Override
    public void saveDictType(DictTypeSaveDto saveDto) {

        valid(null, saveDto);

        DictTypeDo dataObj = AbstractDictTypeConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);
    }

    @Override
    public void updateDictType(Long id, DictTypeSaveDto saveDto) {

        valid(id, saveDto);

        DictTypeDo dataObj = AbstractDictTypeConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);

        dictService.updateDictCache(saveDto.getTypeCode());
    }

    @Override
    public void removeDictType(Long id) {

        String typeCode = getById(id).getTypeCode();

        DictTypeDo dataObj = new DictTypeDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);

        dictService.removeDictByTypeCode(typeCode);
    }

    private void valid(Long id, DictTypeSaveDto saveDto) {

        Long count = count(Wrappers.<DictTypeDo>lambdaQuery()
                .eq(DictTypeDo::getTypeCode, saveDto.getTypeCode())
                .ne(id != null, DictTypeDo::getId, id));
        if (count > 0) {
            throw new BusinessException("编码已存在，请更换。");
        }
    }
}
