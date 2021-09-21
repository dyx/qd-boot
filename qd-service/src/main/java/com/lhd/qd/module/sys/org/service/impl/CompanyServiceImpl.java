package com.lhd.qd.module.sys.org.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.module.sys.org.dao.CompanyMapper;
import com.lhd.qd.module.sys.org.model.converter.AbstractCompanyConverter;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDto;
import com.lhd.qd.module.sys.org.model.entity.CompanyDo;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVo;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVo;
import com.lhd.qd.module.sys.org.service.CompanyService;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVo;
import com.lhd.qd.util.UserUtils;
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
public class CompanyServiceImpl extends QdBaseServiceImpl<CompanyMapper, CompanyDo> implements CompanyService {

    @Override
    public List<CompanyTreeVo> getCompanyTree() {

        UserCacheVo userCacheVo = UserUtils.getCurrentUser();

        // 非超级管理员只能看到本公司及下级公司
        if (!CommonConsts.SUPER_ADMIN_USER_ID.equals(userCacheVo.getId())) {

            List<CompanyDo> doList = list(Wrappers.<CompanyDo>lambdaQuery()
                    .nested(item -> item.eq(CompanyDo::getParentId, userCacheVo.getCompanyId())
                            .or()
                            .eq(CompanyDo::getId, userCacheVo.getCompanyId())));

            CompanyDo detailDo = getById(userCacheVo.getCompanyId());

            return buildTree(detailDo.getParentId(), doList);
        }

        List<CompanyDo> doList = list(null);
        return buildTree(0L, doList);
    }

    @Override
    public CompanyDetailVo getCompanyById(Long id) {

        CompanyDo dataObj = getById(id);

        return AbstractCompanyConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void saveCompany(CompanySaveDto saveDto) {

        CompanyDo dataObj = AbstractCompanyConverter.INSTANCE.saveDto2Do(saveDto);
        save(dataObj);
    }

    @Override
    public void updateCompany(Long id, CompanySaveDto saveDto) {

        CompanyDo dataObj = AbstractCompanyConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeCompany(Long id) {

        if (id != null && id > 0) {

            baseMapper.deleteWithChildren(id, UserUtils.getUserId());
        }
    }

    private static List<CompanyTreeVo> buildTree(Long parentId, List<CompanyDo> doList) {

        List<CompanyTreeVo> treeList =  new ArrayList<>();
        for (CompanyDo dataObj : doList) {

            if (parentId.equals(dataObj.getParentId())) {

                CompanyTreeVo vo = AbstractCompanyConverter.INSTANCE.do2TreeVo(dataObj);
                vo.setChildren(buildTree(dataObj.getId(), doList));

                treeList.add(vo);
            }
        }

        return treeList;
    }
}
