package com.lhd.qd.module.sale.custom.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sale.custom.model.dto.CustomSaveDTO;
import com.lhd.qd.module.sale.custom.model.entity.CustomDO;
import com.lhd.qd.module.sale.custom.model.vo.CustomDetailVO;
import com.lhd.qd.module.sale.custom.model.vo.CustomListVO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.util.TransUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 * @since 2019-07-19
 */
@Mapper
public abstract class AbstractCustomConverter {

    public static AbstractCustomConverter INSTANCE = Mappers.getMapper(AbstractCustomConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @param transVOList
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<CustomListVO> doPage2ListVOPage(IPage<CustomDO> doPage, @Context List<TransVO> transVOList);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract CustomListVO do2ListVO(CustomDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void afterDo2ListVO(CustomDO dataObj, @MappingTarget CustomListVO listVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, listVO, transVOList);
    }

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract CustomDetailVO do2DetailVO(CustomDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void afterDo2DetaiVO(CustomDO dataObj, @MappingTarget CustomDetailVO detailVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, detailVO, transVOList);
    }

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract CustomDO saveDTO2DO(CustomSaveDTO saveDTO);
}
