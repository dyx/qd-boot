package com.lhd.qd.module.sys.dict.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDto;
import com.lhd.qd.module.sys.dict.model.entity.DictDo;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictListVo;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Mapper
public interface DictConverter {

    DictConverter INSTANCE = Mappers.getMapper(DictConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    Page<DictListVo> doPage2ListVoPage(IPage<DictDo> doPage);


    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    DictListVo do2ListVo(DictDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    DictDetailVo do2DetailVo(DictDo dataObj);

    /**
     * do列表 转换为 前端绑定vo列表
     * @param doList
     * @return
     */
    List<DictPageBindVo> doList2PageBindVoList(List<DictDo> doList);

    /**
     * do 转换为 前端绑定vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "value", source = "dictValue")
    @Mapping(target = "desc", source = "dictDesc")
    DictPageBindVo do2PageBindVo(DictDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    DictDo saveDto2Do(DictSaveDto saveDto);
}
