package com.lhd.qd.module.sys.org.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.module.sys.org.dao.CompanyMapper;
import com.lhd.qd.module.sys.org.model.converter.AbstractCompanyConverter;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDTO;
import com.lhd.qd.module.sys.org.model.entity.CompanyDO;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVO;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVO;
import com.lhd.qd.module.sys.org.service.CompanyService;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.service.TransService;
import com.lhd.qd.module.sys.trans.util.SysTransDtoUtils;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVO;
import com.lhd.qd.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 公司 服务实现
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
@Service
public class CompanyServiceImpl extends QdBaseServiceImpl<CompanyMapper, CompanyDO> implements CompanyService {

    @Autowired
    private TransService transService;

    @Override
    public List<CompanyTreeVO> getCompanyTree() {

        UserCacheVO userCacheVO = UserUtils.getCurrentUser();

        // 非超级管理员只能看到本公司及下级公司
        if (!CommonConsts.SUPER_ADMIN_USER_ID.equals(userCacheVO.getId())) {

            List<CompanyDO> doList = list(Wrappers.<CompanyDO>lambdaQuery()
                    .nested(item -> item.eq(CompanyDO::getParentId, userCacheVO.getCompanyId())
                            .or()
                            .eq(CompanyDO::getId, userCacheVO.getCompanyId())));

            CompanyDO detailDO = getById(userCacheVO.getCompanyId());

            return buildTree(detailDO.getParentId(), doList);
        }

        List<CompanyDO> doList = list(null);
        return buildTree(0L, doList);
    }

    @Override
    public CompanyDetailVO getCompanyById(Long id) {

        CompanyDO dataObj = getById(id);

        return AbstractCompanyConverter.INSTANCE.do2DetailVO(dataObj, getTransVO(Collections.singletonList(dataObj)));
    }

    @Override
    public void saveCompany(CompanySaveDTO saveDTO) {

        CompanyDO dataObj = AbstractCompanyConverter.INSTANCE.saveDTO2DO(saveDTO);
        save(dataObj);
    }

    @Override
    public void updateCompany(Long id, CompanySaveDTO saveDTO) {

        CompanyDO dataObj = AbstractCompanyConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeCompany(Long id) {

        if (id != null && id > 0) {

            baseMapper.deleteWithChildren(id, UserUtils.getUserId());
        }
    }

    private static List<CompanyTreeVO> buildTree(Long parentId, List<CompanyDO> doList) {

        List<CompanyTreeVO> treeList =  new ArrayList<>();
        for (CompanyDO dataObj : doList) {

            if (parentId.equals(dataObj.getParentId())) {

                CompanyTreeVO vo = AbstractCompanyConverter.INSTANCE.do2TreeVO(dataObj);
                vo.setChildren(buildTree(dataObj.getId(), doList));

                treeList.add(vo);
            }
        }

        return treeList;
    }

    private List<TransVO> getTransVO(List<CompanyDO> doList) {

        List<TransDTO> dtoList = new ArrayList<>();

        dtoList.add(SysTransDtoUtils.transCompanyNameById(doList.stream().map(CompanyDO::getParentId).collect(toSet()),
                CompanyDO::getParentId, CompanyDetailVO::getParentName));
        return transService.getTransValue(dtoList);
    }
}
