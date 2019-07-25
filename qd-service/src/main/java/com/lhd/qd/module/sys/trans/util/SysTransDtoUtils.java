package com.lhd.qd.module.sys.trans.util;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.lhd.qd.base.BaseDetailVO;
import com.lhd.qd.base.QdBaseDO;
import com.lhd.qd.module.sys.dict.model.entity.DictTypeDO;
import com.lhd.qd.module.sys.org.model.entity.CompanyDO;
import com.lhd.qd.module.sys.org.model.entity.DeptDO;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.user.model.entity.UserDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
public class SysTransDtoUtils {

    public static <M, N> TransDTO transDictTypeDescByCode(Set<String> codeList, SFunction<M, ?> readField, SFunction<N, ?> writeField) {
        return new TransDTO<>(DictTypeDO.class, DictTypeDO::getTypeCode, codeList, DictTypeDO::getTypeDesc, readField, writeField);
    }

    public static <M, N> TransDTO transUserFullNameById(Set<Long> idList,  SFunction<M, ?> readField, SFunction<N, ?> writeField) {
        return new TransDTO<>(UserDO.class, UserDO::getId, idList, UserDO::getFullName, readField, writeField);
    }

    public static TransDTO transCreateUser(Set<Long> idList) {
        return new TransDTO<>(UserDO.class, UserDO::getId, idList, UserDO::getFullName, QdBaseDO::getCreateUserId, BaseDetailVO::getCreateUserName);
    }

    public static TransDTO transUpdateUser(Set<Long> idList) {
        return new TransDTO<>(UserDO.class, UserDO::getId, idList, UserDO::getFullName, QdBaseDO::getUpdateUserId, BaseDetailVO::getUpdateUserName);
    }

    public static <M, N> TransDTO transCompanyNameById(Set<Long> idList,  SFunction<M, ?> readField, SFunction<N, ?> writeField) {
        return new TransDTO<>(CompanyDO.class, CompanyDO::getId, idList, CompanyDO::getCompanyName, readField, writeField);
    }

    public static <M, N> TransDTO transDeptNameById(Set<Long> idList,  SFunction<M, ?> readField, SFunction<N, ?> writeField) {
        return new TransDTO<>(DeptDO.class, DeptDO::getId, idList, DeptDO::getDeptName, readField, writeField);
    }

    public static <M, N> TransDTO transRoleNamesByUserId(List<Map<String, Object>> customValue, SFunction<M, ?> readField, SFunction<N, ?> writeField) {
        return new TransDTO<>(readField, writeField, customValue);
    }
}
