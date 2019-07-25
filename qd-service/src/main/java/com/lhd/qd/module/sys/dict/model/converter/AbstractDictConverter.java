package com.lhd.qd.module.sys.dict.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDTO;
import com.lhd.qd.module.sys.dict.model.entity.DictDO;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVO;
import com.lhd.qd.module.sys.dict.model.vo.DictListVO;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.util.TransUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Mapper
public abstract class AbstractDictConverter {

    public static AbstractDictConverter INSTANCE = Mappers.getMapper(AbstractDictConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @param transVOList
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<DictListVO> doPage2ListVOPage(IPage<DictDO> doPage, @Context List<TransVO> transVOList);


    /**
     * do 转换为 列表vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract DictListVO do2ListVO(DictDO dataObj, @Context List<TransVO> transVOList);

    @AfterMapping
    public void fillJoinField(DictDO dataObj, @MappingTarget DictListVO listVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, listVO, transVOList);
    }

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract DictDetailVO do2DetailVO(DictDO dataObj, @Context List<TransVO> transVOList);

    @AfterMapping
    public void fillJoinField(DictDO dataObj, @MappingTarget DictDetailVO detailVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, detailVO, transVOList);
    }

    /**
     * do列表 转换为 前端绑定vo列表
     * @param doList
     * @return
     */
    public abstract List<DictPageBindVO> doList2PageBindVOList(List<DictDO> doList);

    /**
     * do 转换为 前端绑定vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "value", source = "dictValue")
    @Mapping(target = "desc", source = "dictDesc")
    public abstract DictPageBindVO do2PageBindVO(DictDO dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract DictDO saveDTO2DO(DictSaveDTO saveDTO);
}
