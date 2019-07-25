package com.lhd.qd.module.sys.dict.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDTO;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDO;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVO;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.util.TransUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 * @since 2019-06-01
 */
@Mapper
public abstract class AbstractDictTypeConverter {

    public static AbstractDictTypeConverter INSTANCE = Mappers.getMapper(AbstractDictTypeConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<DictTypeListVO> doPage2ListVOPage(IPage<DictTypeDO> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract DictTypeListVO do2ListVO(DictTypeDO dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract DictTypeDetailVO do2DetailVO(DictTypeDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void fillJoinField(DictTypeDO dataObj, @MappingTarget DictTypeDetailVO detailVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, detailVO, transVOList);
    }

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract DictTypeDO saveDTO2DO(DictTypeSaveDTO saveDTO);
}
