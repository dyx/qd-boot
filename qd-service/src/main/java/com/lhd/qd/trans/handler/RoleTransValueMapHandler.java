package com.lhd.qd.trans.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.module.sys.role.dao.RoleMapper;
import com.lhd.qd.module.sys.role.model.entity.RoleDo;
import com.lhd.qd.util.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
public class RoleTransValueMapHandler implements TransValueMapHandler {

    @Override
    public Map<Object, Map<String, Object>> getValueMap(List<Object> readFieldValueList, String[] writeFieldNames) {

        Map<Object, Map<String, Object>> allValueMap = new HashMap<>(16);
        List<RoleDo> doList = SpringUtils.getBean(RoleMapper.class).selectList(Wrappers.<RoleDo>lambdaQuery().in(RoleDo::getId, readFieldValueList));
        if (doList != null && doList.size() > 0) {
            for (RoleDo dataObj : doList) {
                Map<String, Object> valueMap = new HashMap<>(8);
                for (int i = 0; i < writeFieldNames.length; i++) {
                    String key = writeFieldNames[i];
                    if (key != null && key.length() > 0) {
                        if (i == 0) {
                            valueMap.put(key, dataObj.getRoleName());
                        }
                    }
                }
                allValueMap.put(dataObj.getId(), valueMap);
            }
        }
        return allValueMap;
    }
}
