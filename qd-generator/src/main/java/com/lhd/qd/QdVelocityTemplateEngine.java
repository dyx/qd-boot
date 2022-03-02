package com.lhd.qd;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.File;
import java.util.Map;

/**
 * @author lhd
 */
public class QdVelocityTemplateEngine extends VelocityTemplateEngine {

    @Override
    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
        customFile.forEach((key, value) -> {
            outputFile(new File(key), objectMap, value);
        });
    }
}
