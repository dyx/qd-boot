package com.lhd.qd.module.sys.org.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDTO;
import com.lhd.qd.module.sys.org.model.entity.DeptDO;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVO;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVO;

import java.util.List;

/**
 * <p>
 * 部门 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface DeptService extends QdBaseService<DeptDO> {

    /**
     * 根据公司获取部门
     * @param companyId
     * @return
     */
    List<DeptTreeVO> getDeptTreeByCompanyId(Long companyId);

    /**
     * 根据公司获取部门参照
     * @param companyId
     * @return
     */
    List<DeptTreeVO> getDeptRefTreeByCompanyId(Long companyId);

    /**
     * 部门详情
     * @param id
     * @return
     */
    DeptDetailVO getDeptById(Long id);

    /**
     * 新增部门
     * @param saveDTO
     */
    void saveDept(DeptSaveDTO saveDTO);

    /**
     * 修改部门
     * @param id
     * @param saveDTO
     */
    void updateDept(Long id, DeptSaveDTO saveDTO);

    /**
     * 删除部门
     * @param id
     */
    void removeDept(Long id);

    /**
     * 本公司及子公司下所有部门id
     * @param companyId
     * @return
     */
    String getCompanyAndSubCompanyDeptIds(Long companyId);

    /**
     * 本公司下所有部门id
     * @param companyId
     * @return
     */
    String getCompanyDeptIds(Long companyId);

    /**
     * 本部门及子部门的所有部门id
     * @param id
     * @return
     */
    String getDeptAndSubDeptDeptIds(Long id);
}
