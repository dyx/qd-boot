package com.lhd.qd.module.sys.trans.service;

import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
public interface TransService {

    /**
     * 获取翻译值
     * @param dto
     * @return
     */
    Map<String, Object> getTransValue(TransDTO dto);

    /**
     * 批量获取翻译值
     * @param dtoList
     * @return
     */
    List<TransVO> getTransValue(List<TransDTO> dtoList);
}
