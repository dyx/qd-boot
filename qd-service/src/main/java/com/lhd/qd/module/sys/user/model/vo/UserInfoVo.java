package com.lhd.qd.module.sys.user.model.vo;

import com.lhd.qd.tree.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString
@ApiModel(value = "用户信息数据视图对象")
public class UserInfoVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "角色列表")
    private List<UserRoleVo> roleList;

    @ApiModelProperty(value = "菜单列表")
    private List<AbstractTreeVo> menuList;

    @ApiModelProperty(value = "页面元素字典")
    private Map<Long, String[]> pageElementMap;
}
