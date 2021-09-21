package com.lhd.qd.module.sys.user.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDto;
import com.lhd.qd.module.sys.user.model.entity.UserDo;
import com.lhd.qd.module.sys.user.model.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @return
     */
    Page<UserListVo> doPage2ListVoPage(IPage<UserDo> doPage);

    /**
     * do分页集合 转换为 参照列表vo分页集合
     * @param doPage
     * @return
     */
    Page<UserRefListVo> doPage2RefListVoPage(IPage<UserDo> doPage);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @return
     */
    UserListVo do2ListVo(UserDo dataObj);

    /**
     * do 转换为 参照列表vo
     * @param dataObj
     * @return
     */
    UserRefListVo do2RefListVo(UserDo dataObj);

    /**
     * do 转换为 信息vo
     * @param dataObj
     * @return
     */
    UserInfoVo do2InfoVo(UserDo dataObj);

    /**
     * map 转换为 缓存vo
     * @param map
     * @return
     */
    default UserCacheVo map2CacheVo(Map<String, Object> map) {
        if ( map == null ) {
            return null;
        }

        UserCacheVo userCacheVo = new UserCacheVo();
        userCacheVo.setId(Long.valueOf(String.valueOf(map.get(UserCacheVo.ID))));
        userCacheVo.setUsername((String) map.get(UserCacheVo.USERNAME));
        userCacheVo.setSalt((String) map.get(UserCacheVo.SALT));
        userCacheVo.setPermissionList((List<String>) map.get(UserCacheVo.PERMISSION_LIST));
        userCacheVo.setRoleIdList((List<Long>) map.get(UserCacheVo.ROLE_ID_LIST));
        userCacheVo.setCompanyId(Long.valueOf(String.valueOf(map.get(UserCacheVo.COMPANY_ID))));
        userCacheVo.setDeptId(Long.valueOf(String.valueOf(map.get(UserCacheVo.DEPT_ID))));
        userCacheVo.setCompanyAndSubCompanyDeptIds((String) map.get(UserCacheVo.COMPANY_AND_SUB_COMPANY_DEPT_IDS));
        userCacheVo.setCompanyDeptIds((String) map.get(UserCacheVo.COMPANY_DEPT_IDS));
        userCacheVo.setDeptAndSubDeptDeptIds((String) map.get(UserCacheVo.DEPT_AND_SUB_DEPT_DEPT_IDS));

        return userCacheVo;
    }

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @return
     */
    UserDetailVo do2DetailVo(UserDo dataObj);

    /**
     * 新增dto 转换为 do
     * @param saveDto
     * @return
     */
    UserDo saveDto2Do(UserSaveDto saveDto);
}
