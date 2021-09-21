package com.lhd.qd.module.sys.org.service;

import com.lhd.qd.base.QdBaseService;
import com.lhd.qd.module.sys.org.model.dto.DeptSaveDto;
import com.lhd.qd.module.sys.org.model.entity.DeptDo;
import com.lhd.qd.module.sys.org.model.vo.DeptDetailVo;
import com.lhd.qd.module.sys.org.model.vo.DeptTreeVo;

import java.util.List;

/**
 * <p>
 * 部门 服务
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface DeptService extends QdBaseService<DeptDo> {

    /**
     * 根据公司获取部门
     * @param companyId
     * @return
     */
    List<DeptTreeVo> getDeptTreeByCompanyId(Long companyId);

    /**
     * 根据公司获取部门参照
     * @param companyId
     * @return
     */
    List<DeptTreeVo> getDeptRefTreeByCompanyId(Long companyId);

    /**
     * 部门详情
     * @param id
     * @return
     */
    DeptDetailVo getDeptById(Long id);

    /**
     * 新增部门
     * @param saveDto
     */
    void saveDept(DeptSaveDto saveDto);

    /**
     * 修改部门
     * @param id
     * @param saveDto
     */
    void updateDept(Long id, DeptSaveDto saveDto);

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
