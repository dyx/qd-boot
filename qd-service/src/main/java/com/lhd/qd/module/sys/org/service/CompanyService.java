package com.lhd.qd.module.sys.org.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.org.model.dto.CompanySaveDTO;
import com.lhd.qd.module.sys.org.model.entity.CompanyDO;
import com.lhd.qd.module.sys.org.model.vo.CompanyDetailVO;
import com.lhd.qd.module.sys.org.model.vo.CompanyTreeVO;

import java.util.List;

/**
 * <p>
 * 公司 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface CompanyService extends QdBaseService<CompanyDO> {

    /**
     * 公司树结构
     * @return
     */
    List<CompanyTreeVO> getCompanyTree();

    /**
     * 公司详情
     * @param id
     * @return
     */
    CompanyDetailVO getCompanyById(Long id);

    /**
     * 新增公司
     * @param saveDTO
     */
    void saveCompany(CompanySaveDTO saveDTO);

    /**
     * 修改公司
     * @param id
     * @param saveDTO
     */
    void updateCompany(Long id, CompanySaveDTO saveDTO);

    /**
     * 删除公司
     * @param id
     */
    void removeCompany(Long id);
}
