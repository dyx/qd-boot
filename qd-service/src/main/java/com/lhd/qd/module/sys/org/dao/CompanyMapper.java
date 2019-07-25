package com.lhd.qd.module.sys.org.dao;

import com.lhd.qd.base.QdBaseMapper;
import com.lhd.qd.module.sys.org.model.entity.CompanyDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 公司 数据访问
 * </p>
 *
 * @author lhd
 * @since 2019-07-12
 */
public interface CompanyMapper extends QdBaseMapper<CompanyDO> {

    /**
     * 删除本级及子级
     * @param id
     * @param userId
     */
    void deleteWithChildren(@Param("id") Long id, @Param("userId") Long userId);
}
