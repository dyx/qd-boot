package com.lhd.qd.module.sys.role.model.dto;

import com.lhd.qd.tree.ITree;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色选中菜单树传输对象
 * @author lhd
 * @since 2019-05-23
 */
@Setter
@Getter
@ToString
public class RoleCheckedResourceDTO implements ITree {

    private Long id;
    private Long parentId;
    private String title;
    private Boolean checked;
}
