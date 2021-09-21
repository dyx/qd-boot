package com.lhd.qd.module.sys.user.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.qd.base.QdBaseServiceImpl;
import com.lhd.qd.config.QdProperty;
import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.constant.dict.DataObjEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.user.dao.UserMapper;
import com.lhd.qd.module.sys.user.model.converter.UserConverter;
import com.lhd.qd.module.sys.user.model.dto.UserPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserRefPageQuery;
import com.lhd.qd.module.sys.user.model.dto.UserSaveDto;
import com.lhd.qd.module.sys.user.model.entity.UserDo;
import com.lhd.qd.module.sys.user.model.vo.UserDetailVo;
import com.lhd.qd.module.sys.user.model.vo.UserListVo;
import com.lhd.qd.module.sys.user.model.vo.UserRefListVo;
import com.lhd.qd.module.sys.user.service.UserRoleService;
import com.lhd.qd.module.sys.user.service.UserService;
import com.lhd.qd.trans.annotation.RefTrans;
import com.lhd.qd.trans.annotation.RefTranslating;
import com.lhd.qd.trans.consts.RefTransType;
import com.lhd.qd.util.DataPermissionUtils;
import com.lhd.qd.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhd
 */
@Service
public class UserServiceImpl extends QdBaseServiceImpl<UserMapper, UserDo> implements UserService {

    @Autowired
    private QdProperty qdProperty;

    @Autowired
    private UserRoleService userRoleService;

    @RefTranslating({
            @RefTrans(type = RefTransType.COMPANY, readFieldName = "companyId", writeFieldNames = "companyName"),
            @RefTrans(type = RefTransType.DEPT, readFieldName = "deptId", writeFieldNames = "deptName")
    })
    @Override
    public IPage<UserListVo> pageUser(UserPageQuery query) {

        IPage<UserDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<UserDo>lambdaQuery()
                        .apply(DataPermissionUtils.getSql(DataObjEnum.USER, "id", "dept_id"))
                        .like(StrUtil.isNotEmpty(query.getFullName()), UserDo::getFullName, query.getFullName())
                        .like(StrUtil.isNotEmpty(query.getUsername()), UserDo::getUsername, query.getUsername())
                        .eq(query.getGender() != null, UserDo::getGender, query.getGender())
                        .ge(StrUtil.isNotEmpty(query.getStartCreateTime()), UserDo::getCreateTime, query.getStartCreateTime())
                        .le(StrUtil.isNotEmpty(query.getEndCreateTime()), UserDo::getCreateTime, query.getEndCreateTime())
                        .orderBy(isOrderBy(query, "create_time"), isAsc(query), UserDo::getCreateTime));

        return UserConverter.INSTANCE.doPage2ListVoPage(doPage);
    }

    @RefTranslating({
            @RefTrans(type = RefTransType.COMPANY, readFieldName = "companyId", writeFieldNames = "companyName"),
            @RefTrans(type = RefTransType.DEPT, readFieldName = "deptId", writeFieldNames = "deptName")
    })
    @Override
    public IPage<UserRefListVo> pageRefUser(UserRefPageQuery query) {

        @SuppressWarnings("unchecked")
        IPage<UserDo> doPage = this.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<UserDo>lambdaQuery()
                        .apply(DataPermissionUtils.getSql(DataObjEnum.USER, "id", "dept_id"))
                        .like(StrUtil.isNotEmpty(query.getFullName()), UserDo::getFullName, query.getFullName())
                        .like(StrUtil.isNotEmpty(query.getUsername()), UserDo::getUsername, query.getUsername())
                        .orderBy(isOrderBy(query, "create_time"), isAsc(query), UserDo::getCreateTime));

        return UserConverter.INSTANCE.doPage2RefListVoPage(doPage);
    }

    @RefTranslating({
            @RefTrans(type = RefTransType.COMPANY, readFieldName = "companyId", writeFieldNames = "companyName"),
            @RefTrans(type = RefTransType.DEPT, readFieldName = "deptId", writeFieldNames = "deptName"),
            @RefTrans(type = RefTransType.USER, readFieldName = "createUserId", writeFieldNames = "createUserName"),
            @RefTrans(type = RefTransType.USER, readFieldName = "updateUserId", writeFieldNames = "updateUserName")
    })
    @Override
    public UserDetailVo getUserById(Long id) {

        UserDo dataObj = getById(id);

        return UserConverter.INSTANCE.do2DetailVo(dataObj);
    }

    @Override
    public void saveUser(UserSaveDto saveDto) {

        valid(null, saveDto);

        UserDo dataObj = UserConverter.INSTANCE.saveDto2Do(saveDto);

        String salt = UUID.fastUUID().toString(true);
        dataObj.setPwd(SecurityUtils.md5WithSalt(qdProperty.getUserInitPwd(), salt, qdProperty.getSaltTimes()));
        dataObj.setSalt(salt);

        save(dataObj);
    }

    @Override
    public void updateUser(Long id, UserSaveDto saveDto) {

        valid(id, saveDto);

        UserDo dataObj = UserConverter.INSTANCE.saveDto2Do(saveDto);
        dataObj.setId(id);
        updateById(dataObj);

        // todo 修改公司部门，1、需要更新改人员下客户的所属部门 2、需要更新组织信息缓存
    }

    @Override
    public void removeUser(Long id) {

        if (CommonConsts.SUPER_ADMIN_USER_ID.equals(id)) {
            throw new BusinessException("超级管理员不可删除");
        }

        UserDo dataObj = new UserDo();
        dataObj.setId(id);
        removeByIdWithFill(dataObj);
    }

    @Override
    public void batchRemoveUser(List<Long> idList) {

        if (idList.contains(CommonConsts.SUPER_ADMIN_USER_ID)) {
            throw new BusinessException("超级管理员不可删除");
        }

        removeByIdsWithFill(new UserDo(), idList);
    }

    private void valid(Long id, UserSaveDto saveDto) {

        long count = count(Wrappers.<UserDo>lambdaQuery()
                .eq(UserDo::getUsername, saveDto.getUsername())
                .ne(id != null, UserDo::getId, id));
        if (count > 0) {
            throw new BusinessException("用户名已存在，请更换。");
        }
    }
}
