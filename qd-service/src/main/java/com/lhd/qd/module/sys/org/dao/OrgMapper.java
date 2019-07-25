package com.lhd.qd.module.sys.org.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.org.model.dto.OrgTreeDTO;
import com.lhd.qd.module.sys.org.model.entity.CompanyDO;

import java.util.List;

/**
 * <p>
 * 组织 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface OrgMapper extends QdBaseMapper<CompanyDO> {

    /**
     * 组织树
     * @return
     */
    List<OrgTreeDTO> selectOrgTree();
}
