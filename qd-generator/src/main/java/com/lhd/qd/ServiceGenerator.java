package com.lhd.qd;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @author lhd
 */
public class ServiceGenerator {

    public static void main(String[] args) {

        generateService();
    }

    private static void generateService() {

        AutoGenerator mpg = new AutoGenerator();

        String tableName = ScannerUtils.notBlankScanner("表名（不能为空）");
        String moduleName = ScannerUtils.notBlankScanner("模块名（不能为空）");
        String tableNamePrefix = ScannerUtils.scanner("请输入表名前缀（可空，为空时使用moduleName_）");
        String entityNameInPackage = ScannerUtils.scanner("请输入包路径中的实体名（可空，为空时使用截取表名前缀的值）");

        tableNamePrefix = StringUtils.isNoneBlank(tableNamePrefix)
                ? tableNamePrefix
                : tableName.substring(0, moduleName.length() + 1) ;
        entityNameInPackage = StringUtils.isNoneBlank(entityNameInPackage)
                ? entityNameInPackage : tableName.replace(tableNamePrefix, "");

        String basePackage = String.format("com.lhd.qd.module.%s.%s", moduleName, entityNameInPackage);

        System.out.println("表名前缀：" + tableNamePrefix);
        System.out.println("包路径：" + basePackage);
        ScannerUtils.scanner("请核对以上信息，回车后开始生成");

        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("basePackage", basePackage);
        objectMap.put("excludePackageList", Arrays.asList(
                "com.baomidou.mybatisplus.annotation.TableField",
                "com.baomidou.mybatisplus.annotation.TableId",
                "com.baomidou.mybatisplus.annotation.TableLogic",
                "com.baomidou.mybatisplus.annotation.TableName",
                "com.baomidou.mybatisplus.annotation.Version",
                "com.baomidou.mybatisplus.annotation.FieldFill",
                "com.baomidou.mybatisplus.annotation.IdType",
                "com.lhd.qd.base.QdBaseDO"));
        // 是否继承基础数据对象
        objectMap.put("isExtendsBaseDO", true);
        objectMap.put("baseDOFieldList", CommonConsts.BASE_DO_FIELD_LIST);

        // 逻辑删除字段名
        String logicDeleteFieldName = "deleted";
        // 是否生成 baseResultMap和baseColumnList
        boolean isGenerateXmlBaseInfo = false;

        String projectPath = System.getProperty("user.dir") + File.separator + "qd-service";
        String javaRootPath = String.format("%s/src/main/java/com/lhd/qd/module/%s/%s", projectPath, moduleName, entityNameInPackage);
        String resourcesRootPath = String.format("%s/src/main/resources/mapper/%s", projectPath, moduleName);

        mpg.setGlobalConfig(getGlobalConfig(projectPath, isGenerateXmlBaseInfo));

        mpg.setDataSource(geDataSourceConfig());

        mpg.setPackageInfo(getPackageConfig(basePackage));

        mpg.setCfg(getInjectionConfig(objectMap, resourcesRootPath, javaRootPath));

        mpg.setTemplate(getTemplateConfig());

        mpg.setStrategy(getStrategyConfig(tableName, tableNamePrefix, logicDeleteFieldName));

        mpg.execute();

        System.out.println("生成后端代码成功");
    }

    /**
     * 全局配置
     *
     * @param projectPath
     * @return
     */
    private static GlobalConfig getGlobalConfig(String projectPath, Boolean isGenerateXmlBaseInfo) {

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("lhd");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setBaseResultMap(isGenerateXmlBaseInfo);
        globalConfig.setBaseColumnList(isGenerateXmlBaseInfo);

        return globalConfig;
    }

    /**
     * 包配置
     *
     * @param basePackage
     * @return
     */
    private static PackageConfig getPackageConfig(String basePackage) {

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(basePackage);

        return packageConfig;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private static DataSourceConfig geDataSourceConfig() {

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/qd_admin");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("aA@123456");

        return dataSourceConfig;
    }

    /**
     * 自定义配置
     * @param objectMap
     * @param resourcesRootPath
     * @param javaRootPath
     * @return
     */
    private static InjectionConfig getInjectionConfig(Map<String, Object> objectMap, String resourcesRootPath, String javaRootPath) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // 模板中使用${cfg.key}
                this.setMap(objectMap);
            }
        };
        injectionConfig.setFileOutConfigList(getFileOutConfigList(resourcesRootPath, javaRootPath));

        return injectionConfig;
    }


    /**
     * 自定义文件输出位置
     * @param resourcesRootPath
     * @param javaRootPath
     * @return
     */
    private static List<FileOutConfig> getFileOutConfigList(String resourcesRootPath, String javaRootPath) {
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/service/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/%sMapper.xml", resourcesRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/model/entity/dataObj.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/model/entity/%sDO.java", javaRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/dao/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/dao/%sMapper.java", javaRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/model/converter/converter.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/model/converter/Abstract%sConverter.java", javaRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/model/vo/detailVO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/model/vo/%sDetailVO.java", javaRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/model/vo/listVO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/model/vo/%sListVO.java", javaRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/model/dto/pageQuery.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/model/dto/%sPageQuery.java", javaRootPath, tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/service/model/dto/saveDTO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/model/dto/%sSaveDTO.java", javaRootPath, tableInfo.getEntityName());
            }
        });

        return focList;
    }

    /**
     * 模板配置
     *
     * @return
     */
    private static TemplateConfig getTemplateConfig() {

        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义模板
        templateConfig.setController("/templates/service/controller/controller.java");
        templateConfig.setService("/templates/service/service/service.java");
        templateConfig.setServiceImpl("/templates/service/service/impl/serviceImpl.java");
        // 使用自定义输出路径
        templateConfig.setEntity("");
        templateConfig.setMapper("");
        templateConfig.setXml("");

        return templateConfig;
    }

    private static StrategyConfig getStrategyConfig(String tableName, String tableNamePrefix, String logicDeleteFieldName) {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude(tableName);
        strategyConfig.setTablePrefix(tableNamePrefix);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setLogicDeleteFieldName(logicDeleteFieldName);
        // tinyint转换为boolean并去除字段的is前缀
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);

        return strategyConfig;
    }
}