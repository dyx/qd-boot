package com.lhd.qd.module.sys.role.model.dto;

import com.lhd.qd.tree.ITree;
import lombok.Data;

/**
 * 角色选中菜单树传输对象
 * @author lhd
 * @since 2019-05-23
 */
@Data
public class RoleCheckedResourceDto implements ITree {

    private Long id;
    private Long parentId;
    private String title;
    private Boolean checked;
}
