package com.lhd.qd.module.sys.user.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.util.TransUtils;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDTO;
import com.lhd.qd.module.sys.user.model.entity.UserDO;
import com.lhd.qd.module.sys.user.model.vo.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Mapper
public abstract class AbstractUserConverter {

    public static AbstractUserConverter INSTANCE = Mappers.getMapper(AbstractUserConverter.class);

    /**
     * do分页集合 转换为 列表vo分页集合
     * @param doPage
     * @param transVOList
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<UserListVO> doPage2ListVOPage(IPage<UserDO> doPage, @Context List<TransVO> transVOList);

    /**
     * do分页集合 转换为 参照列表vo分页集合
     * @param doPage
     * @param transVOList
     * @return
     */
    @BeanMapping(resultType = Page.class)
    public abstract IPage<UserRefListVO> doPage2RefListVOPage(IPage<UserDO> doPage, @Context List<TransVO> transVOList);

    /**
     * do 转换为 列表vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract UserListVO do2ListVO(UserDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void afterDo2ListVO(UserDO dataObj, @MappingTarget UserListVO listVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, listVO, transVOList);
    }

    /**
     * do 转换为 参照列表vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract UserRefListVO do2RefListVO(UserDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void afterDo2RefListVO(UserDO dataObj, @MappingTarget UserRefListVO refListVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, refListVO, transVOList);
    }

    /**
     * do 转换为 信息vo
     * @param dataObj
     * @return
     */
    public abstract UserInfoVO do2InfoVO(UserDO dataObj);

    /**
     * map 转换为 缓存vo
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public UserCacheVO map2CacheVO(Map<String, Object> map) {
        if ( map == null ) {
            return null;
        }

        UserCacheVO userCacheVO = new UserCacheVO();
        userCacheVO.setId(Long.valueOf(String.valueOf(map.get(UserCacheVO.ID))));
        userCacheVO.setUsername((String) map.get(UserCacheVO.USERNAME));
        userCacheVO.setSalt((String) map.get(UserCacheVO.SALT));
        userCacheVO.setPermissionList((List<String>) map.get(UserCacheVO.PERMISSION_LIST));
        userCacheVO.setRoleIdList((List<Long>) map.get(UserCacheVO.ROLE_ID_LIST));
        userCacheVO.setCompanyId(Long.valueOf(String.valueOf(map.get(UserCacheVO.COMPANY_ID))));
        userCacheVO.setDeptId(Long.valueOf(String.valueOf(map.get(UserCacheVO.DEPT_ID))));
        userCacheVO.setCompanyAndSubCompanyDeptIds((String) map.get(UserCacheVO.COMPANY_AND_SUB_COMPANY_DEPT_IDS));
        userCacheVO.setCompanyDeptIds((String) map.get(UserCacheVO.COMPANY_DEPT_IDS));
        userCacheVO.setDeptAndSubDeptDeptIds((String) map.get(UserCacheVO.DEPT_AND_SUB_DEPT_DEPT_IDS));

        return userCacheVO;
    }

    /**
     * do 转换为 详情vo
     * @param dataObj
     * @param transVOList
     * @return
     */
    public abstract UserDetailVO do2DetailVO(UserDO dataObj, @Context List<TransVO> transVOList);
    @AfterMapping
    public void afterDo2DetailVO(UserDO dataObj, @MappingTarget UserDetailVO detailVO, @Context List<TransVO> transVOList) {

        TransUtils.trans(dataObj, detailVO, transVOList);
    }

    /**
     * 新增dto 转换为 do
     * @param saveDTO
     * @return
     */
    public abstract UserDO saveDTO2DO(UserSaveDTO saveDTO);
}
