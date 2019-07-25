package com.lhd.qd.module.sys.org.service.impl;

import com.lhd.qd.module.sys.org.dao.OrgMapper;
import com.lhd.qd.module.sys.org.model.converter.AbstractOrgConverter;
import com.lhd.qd.module.sys.org.model.dto.OrgTreeDTO;
import com.lhd.qd.module.sys.org.model.vo.OrgTreeVO;
import com.lhd.qd.module.sys.org.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组织 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgMapper mapper;

    @Override
    public List<OrgTreeVO> getTree() {

        List<OrgTreeDTO> list = mapper.selectOrgTree();

        return buildTree(0L, list);
    }

    private static List<OrgTreeVO> buildTree(Long parentId, List<OrgTreeDTO> dtoList) {

        List<OrgTreeVO> treeList =  new ArrayList<>();
        for (OrgTreeDTO dto : dtoList) {

            if (parentId.equals(dto.getParentId())) {

                OrgTreeVO treeVO = AbstractOrgConverter.INSTANCE.dto2TreeVO(dto);
                treeVO.setChildren(buildTree(dto.getId(), dtoList));

                treeList.add(treeVO);
            }
        }

        return treeList;
    }
}
