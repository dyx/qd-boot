package com.lhd.qd.module.sys.org.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDTO;
import com.lhd.qd.module.sys.org.model.entity.CompanyDO;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVO;
import com.lhd.qd.module.sys.org.model.vo.CompanyListVO;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.util.TransUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Mapper
public abstract class AbstractCompanyConverter {

    public static AbstractCompanyConverter INSTANCE = Mappers.getMapper(AbstractCompanyConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<CompanyListVO> doPage2ListVOPage(IPage<CompanyDO> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract CompanyListVO do2ListVO(CompanyDO dataObj);

    /**
     * do 转换为 树vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "companyName")
    public abstract CompanyTreeVO do2TreeVO(CompanyDO dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract CompanyDetailVO do2DetailVO(CompanyDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void fillJoinField(CompanyDO dataObj, @MappingTarget CompanyDetailVO detailVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, detailVO, transVOList);
    }

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract CompanyDO saveDTO2DO(CompanySaveDTO saveDTO);
}
