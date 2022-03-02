package com.lhd.qd;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.function.Consumer;

/**
 * @author lhd
 */
public class ServiceGenerator {

    public static void main(String[] args) {

        String tableName = "sale_test";
        String projectPath = "/Users/lhd/develop/workspace-opensource/qd-boot/qd-service";
        String moduleName = "sale";
        String entityName = "test";
        String author = "lhd";
        boolean isExtendsBaseDo = true;

        String basePackage = String.format("com.lhd.qd.module.%s.%s", moduleName, StrUtil.toUnderlineCase(entityName).replaceAll(StrUtil.UNDERLINE, "."));
        String javaPath = String.format("%s/src/main/java", projectPath);

        FastAutoGenerator
                .create(new DataSourceConfig.Builder("jdbc:mysql://localhost:3307/qd_admin", "root", "123456"))
                .templateEngine(new QdVelocityTemplateEngine())
                .globalConfig(builder -> {
                    builder.author(author)
                            .disableOpenDir()
                            .outputDir(javaPath);
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage);
                })
                .injectionConfig(builder -> {
                    String outputJavaFilePath = String.format("%s/%s", javaPath, basePackage.replaceAll("\\.", "/"));
                    String upperFirstEntityName = StrUtil.upperFirst(entityName);
                    String mapperXmlPath = String.format("%s/src/main/resources/mapper/%s", projectPath, moduleName);
                    builder.customMap(MapUtil.<String, Object>builder()
                                    .put("basePackage", basePackage)
                                    .put("entity", upperFirstEntityName)
                                    .put("controllerMapping", String.format("/%s/%s", moduleName, StrUtil.toUnderlineCase(entityName).replaceAll(StrUtil.UNDERLINE, "/")))
                                    .put("isExtendsBaseDo", isExtendsBaseDo)
                                    .put("baseDoFieldList", CommonConsts.BASE_DO_FIELD_LIST)
                                    .build())
                            .customFile(MapUtil.<String, String>builder()
                                    .put(String.format("%s/controller/%sController.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/controller/controller.java.vm")
                                    .put(String.format("%s/dao/%sMapper.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/dao/mapper.java.vm")
                                    .put(String.format("%s/model/converter/%sConverter.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/model/converter/converter.java.vm")
                                    .put(String.format("%s/model/dto/%sSaveDto.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/model/dto/saveDto.java.vm")
                                    .put(String.format("%s/model/dto/%sPageQuery.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/model/dto/pageQuery.java.vm")
                                    .put(String.format("%s/model/entity/%sDo.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/model/entity/dataObj.java.vm")
                                    .put(String.format("%s/model/vo/%sDetailVo.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/model/vo/detailVo.java.vm")
                                    .put(String.format("%s/model/vo/%sListVo.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/model/vo/listVo.java.vm")
                                    .put(String.format("%s/service/impl/%sServiceImpl.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/service/impl/serviceImpl.java.vm")
                                    .put(String.format("%s/service/%sService.java", outputJavaFilePath, upperFirstEntityName), "/templates/service/service/service.java.vm")
                                    .put(String.format("%s/%sMapper.xml", mapperXmlPath, upperFirstEntityName), "/templates/service/mapper.xml.vm")
                                    .build());
                })
                .templateConfig((Consumer<TemplateConfig.Builder>) TemplateConfig.Builder::disable)
                .strategyConfig(builder -> {
                    builder.addInclude(tableName)
                            .entityBuilder()
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableRemoveIsPrefix()
                            .addTableFills(new Property("createUserId", FieldFill.INSERT),
                                    new Property("createTime", FieldFill.INSERT),
                                    new Property("updateUserId", FieldFill.UPDATE),
                                    new Property("updateTime", FieldFill.UPDATE))
                            .logicDeletePropertyName(CommonConsts.LOGIC_DELETE_FIELD);
                })
                .execute();
    }
}
