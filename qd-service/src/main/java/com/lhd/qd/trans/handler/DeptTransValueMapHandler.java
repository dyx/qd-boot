package com.lhd.qd.trans.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.module.sys.org.dao.DeptMapper;
import com.lhd.qd.module.sys.org.model.entity.DeptDo;
import com.lhd.qd.util.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
public class DeptTransValueMapHandler implements TransValueMapHandler {

    @Override
    public Map<Object, Map<String, Object>> getValueMap(List<Object> readFieldValueList, String[] writeFieldNames) {

        Map<Object, Map<String, Object>> allValueMap = new HashMap<>(16);
        List<DeptDo> doList = SpringUtils.getBean(DeptMapper.class).selectList(Wrappers.<DeptDo>lambdaQuery().in(DeptDo::getId, readFieldValueList));
        if (doList != null && doList.size() > 0) {
            for (DeptDo dataObj : doList) {
                Map<String, Object> valueMap = new HashMap<>(8);
                for (int i = 0; i < writeFieldNames.length; i++) {
                    String key = writeFieldNames[i];
                    if (key != null && key.length() > 0) {
                        if (i == 0) {
                            valueMap.put(key, dataObj.getDeptName());
                        }
                    }
                }
                allValueMap.put(dataObj.getId(), valueMap);
            }
        }
        return allValueMap;
    }
}
