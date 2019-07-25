package com.lhd.qd.module.sys.org.dao;


import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.org.model.entity.DeptDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 部门 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface DeptMapper extends QdBaseMapper<DeptDO> {

    /**
     * 删除本级及子级
     * @param id
     * @param userId
     */
    void deleteWithChildren(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 本公司及子公司下所有部门id
     * @param companyId
     * @return
     */
    String selectCompanyAndSubCompanyDeptIds(Long companyId);

    /**
     * 本公司下所有部门id
     * @param companyId
     * @return
     */
    String selectCompanyDeptIds(Long companyId);

    /**
     * 本部门及子部门的所有部门id
     * @param id
     * @return
     */
    String selectDeptAndSubDeptDeptIds(Long id);
}
