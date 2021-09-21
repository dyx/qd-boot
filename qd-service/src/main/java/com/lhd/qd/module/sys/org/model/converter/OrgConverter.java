package com.lhd.qd.module.sys.org.model.converter;

import com.lhd.qd.module.sys.org.model.dto.OrgTreeDto;
import com.lhd.qd.module.sys.org.model.vo.OrgTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * @since 2019-07-12
 */
@Mapper
public interface OrgConverter {

    OrgConverter INSTANCE = Mappers.getMapper(OrgConverter.class);

    /**
     * do 转换为 树vo
     * @param dto
     * @return
     */
    @Mapping(target = "id", expression = "java( Math.abs(dto.getId()) )")
    @Mapping(target = "parentId", expression = "java( Math.abs(dto.getParentId()) )")
    OrgTreeVo dto2TreeVo(OrgTreeDto dto);

}
