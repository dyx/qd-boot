package com.lhd.qd.module.sys.org.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDTO;
import com.lhd.qd.module.sys.org.model.entity.DeptDO;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVO;
import com.lhd.qd.module.sys.org.model.vo.DeptListVO;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVO;
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
public abstract class AbstractDeptConverter {

    public static AbstractDeptConverter INSTANCE = Mappers.getMapper(AbstractDeptConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<DeptListVO> doPage2ListVOPage(IPage<DeptDO> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    public abstract DeptListVO do2ListVO(DeptDO dataObj);

    /**
     * do 转换为 树vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "deptName")
    public abstract DeptTreeVO do2TreeVO(DeptDO dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract DeptDetailVO do2DetailVO(DeptDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void fillJoinField(DeptDO dataObj, @MappingTarget DeptDetailVO detailVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, detailVO, transVOList);
    }

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract DeptDO saveDTO2DO(DeptSaveDTO saveDTO);
}
