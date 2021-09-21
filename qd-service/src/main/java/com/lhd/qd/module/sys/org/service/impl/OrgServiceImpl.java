package com.lhd.qd.module.sys.org.service.impl;

import com.lhd.qd.module.sys.org.dao.OrgMapper;
import com.lhd.qd.module.sys.org.model.converter.OrgConverter;
import com.lhd.qd.module.sys.org.model.dto.OrgTreeDto;
import com.lhd.qd.module.sys.org.model.vo.OrgTreeVo;
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
    public List<OrgTreeVo> getTree() {

        List<OrgTreeDto> list = mapper.selectOrgTree();

        return buildTree(0L, list);
    }

    private static List<OrgTreeVo> buildTree(Long parentId, List<OrgTreeDto> dtoList) {

        List<OrgTreeVo> treeList =  new ArrayList<>();
        for (OrgTreeDto dto : dtoList) {

            if (parentId.equals(dto.getParentId())) {

                OrgTreeVo treeVo = OrgConverter.INSTANCE.dto2TreeVo(dto);
                treeVo.setChildren(buildTree(dto.getId(), dtoList));

                treeList.add(treeVo);
            }
        }

        return treeList;
    }
}
