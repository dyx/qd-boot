package com.lhd.qd.module.sys.org.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.org.model.dto.OrgTreeDto;
import com.lhd.qd.module.sys.org.model.entity.CompanyDo;

import java.util.List;

/**
 * <p>
 * 组织 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface OrgMapper extends QdBaseMapper<CompanyDo> {

    /**
     * 组织树
     * @return
     */
    List<OrgTreeDto> selectOrgTree();
}
