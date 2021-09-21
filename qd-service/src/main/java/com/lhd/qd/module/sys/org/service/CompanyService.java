package com.lhd.qd.module.sys.org.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDto;
import com.lhd.qd.module.sys.org.model.entity.CompanyDo;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVo;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVo;

import java.util.List;

/**
 * <p>
 * 公司 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface CompanyService extends QdBaseService<CompanyDo> {

    /**
     * 公司树结构
     * @return
     */
    List<CompanyTreeVo> getCompanyTree();

    /**
     * 公司详情
     * @param id
     * @return
     */
    CompanyDetailVo getCompanyById(Long id);

    /**
     * 新增公司
     * @param saveDto
     */
    void saveCompany(CompanySaveDto saveDto);

    /**
     * 修改公司
     * @param id
     * @param saveDto
     */
    void updateCompany(Long id, CompanySaveDto saveDto);

    /**
     * 删除公司
     * @param id
     */
    void removeCompany(Long id);
}
