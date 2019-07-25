package com.lhd.qd.module.sys.trans.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lhd.qd.module.sys.trans.dao.TransMapper;
import com.lhd.qd.module.sys.trans.model.dto.TransDTO;
import com.lhd.qd.module.sys.trans.model.vo.TransVO;
import com.lhd.qd.util.LambdaConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Service
public class TransServiceImpl implements TransService {

    @Autowired
    private TransMapper mapper;

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getTransValue(TransDTO dto) {

        if (dto == null) {
            return null;
        }

        if (dto.getReadField() == null || dto.getWriteField() == null) {
            return null;
        }

        List<Map<String, Object>> list;
        String idKey;
        String nameKey;
        if (dto.getDoClass() != null) {

            if (dto.getIdColumn() == null || dto.getNameColumn() == null) {
                return null;
            }

            if (dto.getIdValues() == null || dto.getIdValues().size() == 0) {
                return null;
            }

            LambdaQueryWrapper queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.select(dto.getIdColumn(), dto.getNameColumn())
                    .in(dto.getIdColumn(), dto.getIdValues());

            String tableName = TableInfoHelper.getTableInfo(dto.getDoClass()).getTableName();
            list = mapper.selectNamesByIds(tableName, queryWrapper);

            idKey = LambdaConvertUtils.lambdaToColumn(dto.getIdColumn());
            nameKey = LambdaConvertUtils.lambdaToColumn(dto.getNameColumn());
        } else {

            list = dto.getCustomValue();
            idKey = "id";
            nameKey = "name";
        }

        Map<String, Object> resultMap = new HashMap<>(16);
        for (Map<String, Object> item : list) {
            resultMap.put(String.valueOf(item.get(idKey)), item.get(nameKey));
        }

        return resultMap;
    }

    @Override
    public List<TransVO> getTransValue(List<TransDTO> dtoList) {

        if (dtoList == null || dtoList.size() == 0) {
            return null;
        }

        List<TransVO> voList = new ArrayList<>();
        for (TransDTO dto: dtoList) {

            TransVO vo = new TransVO();
            vo.setReadField(LambdaConvertUtils.lambdaToProperty(dto.getReadField()));
            vo.setWriteField(LambdaConvertUtils.lambdaToProperty(dto.getWriteField()));
            vo.setWriteFieldType(dto.getWriteMethodParamType());
            vo.setTransValue(getTransValue(dto));
            voList.add(vo);
        }

        return voList;
    }
}
