package com.lhd.qd.module.sys.trans.util;

import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.util.ReflectUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 关联翻译工具
 * @author lhd
 */
@Slf4j
public class TransUtils {

    public static void trans(Object sourceEntity, Object targetEntity, List<TransVO> transVOList) {

        if (sourceEntity == null || targetEntity == null
                || transVOList == null || transVOList.size() == 0) {
            return;
        }
        for (TransVO transVO : transVOList) {

            try {
                // 从id字段获取值
                Object readValue = ReflectUtils.getFieldValue(sourceEntity, transVO.getReadField());
                if (readValue != null) {

                    // 赋值给name字段
                    ReflectUtils.setFieldValue(targetEntity,
                            transVO.getWriteField(),
                            transVO.getTransValue().get(readValue.toString()),
                            transVO.getWriteFieldType());
                }
            } catch (Exception e) {
                log.debug("翻译错误", e);
            }
        }
    }
}
