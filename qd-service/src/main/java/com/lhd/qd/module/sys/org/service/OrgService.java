package com.lhd.qd.module.sys.org.service;

import com.lhd.qd.module.sys.org.model.vo.OrgTreeVO;

import java.util.List;

/**
 * <p>
 * 组织 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface OrgService {

    /**
     * 组织树
     * @return
     */
    List<OrgTreeVO> getTree();
}
