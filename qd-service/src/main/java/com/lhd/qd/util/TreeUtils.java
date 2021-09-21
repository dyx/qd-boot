package com.lhd.qd.util;

import com.lhd.qd.tree.AbstractTreeVo;
import com.lhd.qd.tree.AbstractTreeVoFactory;
import com.lhd.qd.tree.ITree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树结构构建工具
 * @author lhd
 */
public class TreeUtils {

    /**
     * 构建菜单树
     * @param factoryClass 实体转换工厂
     * @param parentId 上级id
     * @param entityList 实体列表
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends ITree> List<AbstractTreeVo> buildTree(Class<? extends AbstractTreeVoFactory> factoryClass,
                                                                   Long parentId,
                                                                   List<T> entityList) throws IllegalAccessException, InstantiationException {
        List<AbstractTreeVo> treeList =  new ArrayList<>();
        for (T entity : entityList) {

            if (parentId.equals(entity.getParentId())) {

                AbstractTreeVo vo = factoryClass.newInstance().produce(entity);
                vo.setChildren(buildTree(factoryClass, entity.getId(), entityList));

                treeList.add(vo);
            }
        }

        return treeList;
    }

    private static Boolean isLeaf(List<AbstractTreeVo> list) {
        return list == null || list.size() == 0;
    }
}
