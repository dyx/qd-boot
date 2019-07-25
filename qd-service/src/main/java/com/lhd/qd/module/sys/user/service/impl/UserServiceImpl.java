package com.lhd.qd.module.sys.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseDO;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.config.QdProperty;
import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.constant.dict.DataObjEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.module.sys.trans.service.TransService;
import com.lhd.qd.module.sys.trans.util.SysTransDtoUtils;
import com.lhd.qd.module.sys.user.dao.UserMapper;
import com.lhd.qd.module.sys.user.model.converter.AbstractUserConverter;
import com.lhd.qd.module.sys.user.model.dto.UserPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserRefPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDTO;
import com.lhd.qd.module.sys.user.model.entity.UserDO;
import com.lhd.qd.module.sys.user.model.vo.UserDetailVO;
import com.lhd.qd.module.sys.user.model.vo.UserListVO;
import com.lhd.qd.module.sys.user.model.vo.UserRefListVO;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import com.lhd.qd.module.sys.user.service.UserService;
import com.lhd.qd.util.DataPermissionUtils;
import com.lhd.qd.util.SecurityUtils;
import com.lhd.qd.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toSet;

/**
 * @author lhd
 */
@Service
public class UserServiceImpl extends QdBaseServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private QdProperty qdProperty;

    @Autowired
    private TransService transService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public IPage<UserListVO> pageUser(UserPageQuery query) {

        @SuppressWarnings("unchecked")
        IPage<UserDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<UserDO>lambdaQuery()
                        .apply(DataPermissionUtils.getSql(DataObjEnum.USER, UserDO::getId, UserDO::getDeptId))
                        .like(StringUtils.isNotEmpty(query.getFullName()), UserDO::getFullName, query.getFullName())
                        .like(StringUtils.isNotEmpty(query.getUsername()), UserDO::getUsername, query.getUsername())
                        .eq(query.getGender() != null, UserDO::getGender, query.getGender())
                        .ge(StringUtils.isNotEmpty(query.getStartCreateTime()), UserDO::getCreateTime, query.getStartCreateTime())
                        .le(StringUtils.isNotEmpty(query.getEndCreateTime()), UserDO::getCreateTime, query.getEndCreateTime())
                        .orderBy(isOrderBy(query, UserDO::getCreateTime), isAsc(query), UserDO::getCreateTime));

        return AbstractUserConverter.INSTANCE.doPage2ListVOPage(doPage, getTransVO(doPage.getRecords()));
    }

    @Override
    public IPage<UserRefListVO> pageRefUser(UserRefPageQuery query) {

        @SuppressWarnings("unchecked")
        IPage<UserDO> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<UserDO>lambdaQuery()
                        .apply(DataPermissionUtils.getSql(DataObjEnum.USER, UserDO::getId, UserDO::getDeptId))
                        .like(StringUtils.isNotEmpty(query.getFullName()), UserDO::getFullName, query.getFullName())
                        .like(StringUtils.isNotEmpty(query.getUsername()), UserDO::getUsername, query.getUsername())
                        .orderBy(isOrderBy(query, UserDO::getCreateTime), isAsc(query), UserDO::getCreateTime));

        return AbstractUserConverter.INSTANCE.doPage2RefListVOPage(doPage, getTransVO(doPage.getRecords()));
    }

    @Override
    public UserDetailVO getUserById(Long id) {

        UserDO dataObj = getById(id);

        return AbstractUserConverter.INSTANCE.do2DetailVO(dataObj, getTransVO(Collections.singletonList(dataObj)));
    }

    @Override
    public void saveUser(UserSaveDTO saveDTO) {

        vaild(null, saveDTO);

        UserDO dataObj = AbstractUserConverter.INSTANCE.saveDTO2DO(saveDTO);

        String salt = UuidUtils.getId();
        dataObj.setPwd(SecurityUtils.md5WithSalt(qdProperty.getUserInitPwd(), salt, qdProperty.getSaltTimes()));
        dataObj.setSalt(salt);

        save(dataObj);
    }

    @Override
    public void updateUser(Long id, UserSaveDTO saveDTO) {

        vaild(id, saveDTO);

        UserDO dataObj = AbstractUserConverter.INSTANCE.saveDTO2DO(saveDTO);
        dataObj.setId(id);
        updateById(dataObj);

        // todo 修改公司部门，1、需要更新改人员下客户的所属部门 2、需要更新组织信息缓存
    }

    @Override
    public void removeUser(Long id) {

        if (CommonConsts.SUPER_ADMIN_USER_ID.equals(id)) {
            throw new BusinessException("超级管理员不可删除");
        }

        UserDO dataObj = new UserDO();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    @Override
    public void batchRemoveUser(List<Long> idList) {

        if (idList.contains(CommonConsts.SUPER_ADMIN_USER_ID)) {
            throw new BusinessException("超级管理员不可删除");
        }

        removeByIdsWithFill(new UserDO(), idList);
    }

    private void vaild(Long id, UserSaveDTO saveDTO) {

        Integer count = count(Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getUsername, saveDTO.getUsername())
                .ne(id != null, UserDO::getId, id));
        if (count > 0) {
            throw new BusinessException("用户名已存在，请更换。");
        }
    }

    private List<TransVO> getTransVO(List<UserDO> doList) {

        List<TransDTO> dtoList = new ArrayList<>();
        dtoList.add(SysTransDtoUtils.transCreateUser(doList.stream().map(QdBaseDO::getCreateUserId).collect(toSet())));
        dtoList.add(SysTransDtoUtils.transUpdateUser(doList.stream().map(QdBaseDO::getUpdateUserId).collect(toSet())));

        dtoList.add(SysTransDtoUtils.transCompanyNameById(doList.stream().map(UserDO::getCompanyId).collect(toSet()),
                UserDO::getCompanyId, UserDetailVO::getCompanyName));

        dtoList.add(SysTransDtoUtils.transDeptNameById(doList.stream().map(UserDO::getDeptId).collect(toSet()),
                UserDO::getDeptId, UserDetailVO::getDeptName));

        dtoList.add(SysTransDtoUtils.transRoleNamesByUserId(
                userRoleService.getRoleNamesByUserId(doList.stream().map(UserDO::getId).collect(toSet())),
                UserDO::getId, UserDetailVO::getRoleNames));

        return transService.getTransValue(dtoList);
    }
}
