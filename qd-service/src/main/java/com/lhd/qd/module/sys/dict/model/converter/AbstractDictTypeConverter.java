package com.lhd.qd.module.sys.dict.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.dict.model.dto.DictTypeSaveDto;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictTypeListVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

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
    public abstract IPage<DictTypeListVo> doPage2ListVoPage(IPage<DictTypeDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract DictTypeListVo do2ListVo(DictTypeDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    public abstract DictTypeDetailVo do2DetailVo(DictTypeDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    public abstract DictTypeDo saveDto2Do(DictTypeSaveDto saveDto);
}
