package com.lhd.qd.trans.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.module.sys.org.dao.CompanyMapper;
import com.lhd.qd.module.sys.org.model.entity.CompanyDo;
import com.lhd.qd.util.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
public class CompanyTransValueMapHandler implements TransValueMapHandler {

    @Override
    public Map<Object, Map<String, Object>> getValueMap(List<Object> readFieldValueList, String[] writeFieldNames) {

        Map<Object, Map<String, Object>> allValueMap = new HashMap<>(16);
        List<CompanyDo> doList = SpringUtils.getBean(CompanyMapper.class).selectList(Wrappers.<CompanyDo>lambdaQuery().in(CompanyDo::getId, readFieldValueList));
        if (doList != null && doList.size() > 0) {
            for (CompanyDo dataObj : doList) {
                Map<String, Object> valueMap = new HashMap<>(8);
                for (int i = 0; i < writeFieldNames.length; i++) {
                    String key = writeFieldNames[i];
                    if (key != null && key.length() > 0) {
                        if (i == 0) {
                            valueMap.put(key, dataObj.getCompanyName());
                        }
                    }
                }
                allValueMap.put(dataObj.getId(), valueMap);
            }
        }
        return allValueMap;
    }
}
