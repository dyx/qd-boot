package com.lhd.qd.module.sale.custom.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.dict.DataObjEnum;
import com.lhd.qd.module.sale.custom.dao.CustomMapper;
import com.lhd.qd.module.sale.custom.model.converter.CustomConverter;
import com.lhd.qd.module.sale.custom.model.dto.CustomPageQuery;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDto;
import com.lhd.qd.module.sale.custom.model.entity.CustomDo;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVo;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVo;
import com.lhd.qd.module.sale.custom.service.CustomService;
import com.lhd.qd.trans.annotation.RefTrans;
import com.lhd.qd.trans.annotation.RefTranslating;
import com.lhd.qd.trans.consts.RefTransType;
import com.lhd.qd.util.DataPermissionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-19
 */
@Service
public class CustomServiceImpl extends QdBaseServiceImpl<CustomMapper, CustomDo> implements CustomService {

    @RefTranslating({
            @RefTrans(type = RefTransType.USER, readFieldName = "ownerId", writeFieldNames = "ownerName"),
            @RefTrans(type = RefTransType.DEPT, readFieldName = "ownerDeptId", writeFieldNames = "ownerDeptName")
    })
    @Override
    public IPage<CustomListVo> pageCustom(CustomPageQuery query) {

        IPage<CustomDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<CustomDo>lambdaQuery()
                        .apply(DataPermissionUtils.getSql(DataObjEnum.CUSTOM))
                        .like(StrUtil.isNotEmpty(query.getCustomName()), CustomDo::getCustomName, query.getCustomName()));

        return CustomConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @RefTranslating({
            @RefTrans(type = RefTransType.USER, readFieldName = "ownerId", writeFieldNames = "ownerName"),
            @RefTrans(type = RefTransType.DEPT, readFieldName = "ownerDeptId", writeFieldNames = "ownerDeptName"),
            @RefTrans(type = RefTransType.USER, readFieldName = "createUserId", writeFieldNames = "createUserName"),
            @RefTrans(type = RefTransType.USER, readFieldName = "updateUserId", writeFieldNames = "updateUserName")
    })
    @Override
    public CustomDetailVo getCustomById(Long id) {

        CustomDo dataObj = getById(id);

        return CustomConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void saveCustom(CustomSaveDto saveDto) {

        CustomDo dataObj = CustomConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);
    }

    @Override
    public void updateCustom(Long id, CustomSaveDto saveDto) {

        CustomDo dataObj = CustomConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeCustom(Long id) {

        CustomDo dataObj = new CustomDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    @Override
    public void batchRemoveCustom(List<Long> idList) {

        removeByIdsWithFill(new CustomDo(), idList);
    }
}
