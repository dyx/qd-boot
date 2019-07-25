package com.lhd.qd.module.sys.org.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.module.sys.org.dao.DeptMapper;
import com.lhd.qd.module.sys.org.model.converter.AbstractDeptConverter;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDTO;
import com.lhd.qd.module.sys.org.model.entity.DeptDO;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVO;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVO;
import com.lhd.qd.module.sys.org.service.DeptService;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.service.TransService;
import com.lhd.qd.module.sys.trans.util.SysTransDtoUtils;
import com.lhd.qd.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 部门 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@Service
public class DeptServiceImpl extends QdBaseServiceImpl<DeptMapper, DeptDO> implements DeptService {

    @Autowired
    private TransService transService;

    @Override
    public List<DeptTreeVO> getDeptTreeByCompanyId(Long companyId) {

        List<DeptDO> doList = list(Wrappers.<DeptDO>lambdaQuery().eq(DeptDO::getCompanyId, companyId));

        return buildTree(0L, doList);
    }

    @Override
    public List<DeptTreeVO> getDeptRefTreeByCompanyId(Long companyId) {

        List<DeptDO> doList = list(Wrappers.<DeptDO>lambdaQuery().eq(DeptDO::getCompanyId, companyId));

        return buildTree(0L, doList);
    }

    @Override
    public DeptDetailVO getDeptById(Long id) {

        DeptDO dataObj = getById(id);

        return AbstractDeptConverter.INSTANCE.do2DetailVO(dataObj, getTransVO(Collections.singletonList(dataObj)));
    }

    @Override
    public void saveDept(DeptSaveDTO saveDTO) {

        DeptDO dataObj = AbstractDeptConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);
    }

    @Override
    public void updateDept(Long id, DeptSaveDTO saveDTO) {

        DeptDO dataObj = AbstractDeptConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeDept(Long id) {

        if (id != null && id > 0) {

            baseMapper.deleteWithChildren(id, UserUtils.getUserId());
        }
    }

    @Override
    public String getCompanyAndSubCompanyDeptIds(Long companyId) {
        return baseMapper.selectCompanyAndSubCompanyDeptIds(companyId);
    }

    @Override
    public String getCompanyDeptIds(Long companyId) {
        return baseMapper.selectCompanyDeptIds(companyId);
    }

    @Override
    public String getDeptAndSubDeptDeptIds(Long id) {
        return baseMapper.selectDeptAndSubDeptDeptIds(id);
    }

    private static List<DeptTreeVO> buildTree(Long parentId, List<DeptDO> doList) {

        List<DeptTreeVO> treeList =  new ArrayList<>();
        for (DeptDO dataObj : doList) {

            if (parentId.equals(dataObj.getParentId())) {

                DeptTreeVO vo = AbstractDeptConverter.INSTANCE.do2TreeVO(dataObj);
                vo.setChildren(buildTree(dataObj.getId(), doList));

                treeList.add(vo);
            }
        }

        return treeList;
    }

    private List<TransVO> getTransVO(List<DeptDO> doList) {

        List<TransDTO> dtoList = new ArrayList<>();

        dtoList.add(SysTransDtoUtils.transCompanyNameById(doList.stream().map(DeptDO::getCompanyId).collect(toSet()),
                DeptDO::getCompanyId, DeptDetailVO::getCompanyName));

        dtoList.add(SysTransDtoUtils.transDeptNameById(doList.stream().map(DeptDO::getParentId).collect(toSet()),
                DeptDO::getParentId, DeptDetailVO::getParentName));

        return transService.getTransValue(dtoList);
    }


}
