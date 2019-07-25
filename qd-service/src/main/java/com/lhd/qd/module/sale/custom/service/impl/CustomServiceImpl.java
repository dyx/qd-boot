package com.lhd.qd.module.sale.custom.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseDO;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.dict.DataObjEnum;
import com.lhd.qd.module.sale.custom.dao.CustomMapper;
import com.lhd.qd.module.sale.custom.model.converter.AbstractCustomConverter;
import com.lhd.qd.module.sale.custom.model.dto.CustomPageQuery;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDTO;
import com.lhd.qd.module.sale.custom.model.entity.CustomDO;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVO;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVO;
import com.lhd.qd.module.sale.custom.service.CustomService;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.service.TransService;
import com.lhd.qd.module.sys.trans.util.SysTransDtoUtils;
import com.lhd.qd.util.DataPermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 客户 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
@Service
public class CustomServiceImpl extends QdBaseServiceImpl<CustomMapper, CustomDO> implements CustomService {

    @Autowired
    private TransService transService;

    @Override
    public IPage<CustomListVO> pageCustom(CustomPageQuery query) {

        IPage<CustomDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<CustomDO>lambdaQuery()
                        .apply(DataPermissionUtils.getSql(DataObjEnum.CUSTOM))
                        .like(StringUtils.isNotEmpty(query.getCustomName()), CustomDO::getCustomName, query.getCustomName()));

        return AbstractCustomConverter.INSTANCE.doPage2ListVOPage(doPage, getTransVO(doPage.getRecords()));
    }

    @Override
    public CustomDetailVO getCustomById(Long id) {

        CustomDO dataObj = getById(id);

        return AbstractCustomConverter.INSTANCE.do2DetailVO(dataObj, getTransVO(Collections.singletonList(dataObj)));
    }

    @Override
    public void saveCustom(CustomSaveDTO saveDTO) {

        CustomDO dataObj = AbstractCustomConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);
    }

    @Override
    public void updateCustom(Long id, CustomSaveDTO saveDTO) {

        CustomDO dataObj = AbstractCustomConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeCustom(Long id) {

        CustomDO dataObj = new CustomDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    @Override
    public void batchRemoveCustom(List<Long> idList) {

        removeByIdsWithFill(new CustomDO(), idList);
    }

    private List<TransVO> getTransVO(List<CustomDO> doList) {

        List<TransDTO> dtoList = new ArrayList<>();
        dtoList.add(SysTransDtoUtils.transCreateUser(doList.stream().map(QdBaseDO::getCreateUserId).collect(toSet())));
        dtoList.add(SysTransDtoUtils.transUpdateUser(doList.stream().map(QdBaseDO::getUpdateUserId).collect(toSet())));

        dtoList.add(SysTransDtoUtils.transUserFullNameById(doList.stream().map(CustomDO::getOwnerId).collect(toSet()),
                CustomDO::getOwnerId, CustomDetailVO::getOwnerName));
        dtoList.add(SysTransDtoUtils.transDeptNameById(doList.stream().map(CustomDO::getOwnerDeptId).collect(toSet()),
                CustomDO::getOwnerDeptId, CustomDetailVO::getOwnerDeptName));

        return transService.getTransValue(dtoList);
    }
}
