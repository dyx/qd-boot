package com.lhd.qd.module.sys.org.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDto;
import com.lhd.qd.module.sys.org.model.entity.DeptDo;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVo;
import com.lhd.qd.module.sys.org.model.vo.DeptListVo;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Mapper
public interface DeptConverter {

   DeptConverter INSTANCE = Mappers.getMapper(DeptConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    Page<DeptListVo> doPage2ListVoPage(IPage<DeptDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    DeptListVo do2ListVo(DeptDo dataObj);

    /**
     * do 转换为 树vo
     * @param dataObj
     * @return
     */
    @Mapping(target = "title", source = "deptName")
    DeptTreeVo do2TreeVo(DeptDo dataObj);

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    DeptDetailVo do2DetailVo(DeptDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    DeptDo saveDto2Do(DeptSaveDto saveDto);
}
