package com.lhd.qd.module.sys.org.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.module.sys.org.dao.DeptMapper;
import com.lhd.qd.module.sys.org.model.converter.AbstractDeptConverter;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDto;
import com.lhd.qd.module.sys.org.model.entity.DeptDo;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVo;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVo;
import com.lhd.qd.module.sys.org.service.DeptService;
import com.lhd.qd.util.UserUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@Service
public class DeptServiceImpl extends QdBaseServiceImpl<DeptMapper, DeptDo> implements DeptService {

    @Override
    public List<DeptTreeVo> getDeptTreeByCompanyId(Long companyId) {

        List<DeptDo> doList = list(Wrappers.<DeptDo>lambdaQuery().eq(DeptDo::getCompanyId, companyId));

        return buildTree(0L, doList);
    }

    @Override
    public List<DeptTreeVo> getDeptRefTreeByCompanyId(Long companyId) {

        List<DeptDo> doList = list(Wrappers.<DeptDo>lambdaQuery().eq(DeptDo::getCompanyId, companyId));

        return buildTree(0L, doList);
    }

    @Override
    public DeptDetailVo getDeptById(Long id) {

        DeptDo dataObj = getById(id);

        return AbstractDeptConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void saveDept(DeptSaveDto saveDto) {

        DeptDo dataObj = AbstractDeptConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);
    }

    @Override
    public void updateDept(Long id, DeptSaveDto saveDto) {

        DeptDo dataObj = AbstractDeptConverter.INSTANCE.saveDto2Do(saveDto);
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

    private static List<DeptTreeVo> buildTree(Long parentId, List<DeptDo> doList) {

        List<DeptTreeVo> treeList =  new ArrayList<>();
        for (DeptDo dataObj : doList) {

            if (parentId.equals(dataObj.getParentId())) {

                DeptTreeVo vo = AbstractDeptConverter.INSTANCE.do2TreeVo(dataObj);
                vo.setChildren(buildTree(dataObj.getId(), doList));

                treeList.add(vo);
            }
        }

        return treeList;
    }
}
