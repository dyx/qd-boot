//package com.lhd.qd;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.FileOutConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.TemplateConfig;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author lhd
// */
//public class PageGenerator {
//
//    public static void main(String[] args) {
//
//        generatePage();
//    }
//
//    private static void generatePage() {
//
//        AutoGenerator mpg = new AutoGenerator();
//
//        String tableName = ScannerUtils.notBlankScanner("表名（不能为空）");
//        String moduleName = ScannerUtils.notBlankScanner("模块名（不能为空）");
//        String tableNamePrefix = ScannerUtils.scanner("请输入表名前缀（可空，为空时使用moduleName_）");
//        String entityNameInPackage = ScannerUtils.scanner("请输入包路径中的实体名（可空，为空时使用截取表名前缀的值）");
//
//        tableNamePrefix = StringUtils.isNoneBlank(tableNamePrefix)
//                ? tableNamePrefix
//                : tableName.substring(0, moduleName.length() + 1) ;
//        entityNameInPackage = StringUtils.isNoneBlank(entityNameInPackage)
//                ? entityNameInPackage : tableName.replace(tableNamePrefix, "");
//
//        String projectPath = System.getProperty("user.dir") + "/qd-page";
//        String apiPath = projectPath + "/src/api/module/" + moduleName;
//        String storePath = projectPath + "/src/store/module/" + moduleName;
//        String viewPath = projectPath + "/src/view/module/" + moduleName + "/" + entityNameInPackage;
//
//        System.out.println("表名" + tableName);
//        System.out.println("表名前缀：" + tableNamePrefix);
//        System.out.println("模块名：" + moduleName);
//        System.out.println("api代码路径：" + apiPath);
//        System.out.println("store代码路径：" + storePath);
//        System.out.println("view代码路径：" + viewPath);
//        ScannerUtils.scanner("请核对以上信息，回车后开始生成");
//
//        Map<String, Object> objectMap = new HashMap<>(16);
//        objectMap.put("moduleName", moduleName);
//        objectMap.put("entityNameInPackage", entityNameInPackage);
//        objectMap.put("baseDoFieldList", CommonConsts.BASE_DO_FIELD_LIST);
//        objectMap.put("primaryKey", "id");
//        objectMap.put("logicDeleteFieldName", "isDeleted");
//
//        mpg.setDataSource(geDataSourceConfig());
//
//        mpg.setCfg(getInjectionConfig(objectMap, entityNameInPackage, apiPath, storePath, viewPath));
//
//        mpg.setTemplate(getTemplateConfig());
//
//        mpg.setStrategy(getStrategyConfig(tableName, tableNamePrefix));
//
//        mpg.execute();
//
//        System.out.println("生成前端代码成功");
//        System.out.println(String.format("请修改 %s/src/store/index.js 文件", projectPath));
//        System.out.println("导入如下包：");
//        System.out.println(String.format("import %s from './module/%s/%s'", entityNameInPackage, moduleName, entityNameInPackage));
//        System.out.println("增加如下模块：");
//        System.out.println(entityNameInPackage);
//    }
//
//    private static InjectionConfig getInjectionConfig(Map<String, Object> objectMap, String entityNameInPackage,
//                                                      String apiPath, String storePath, String viewPath) {
//
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // 模板中使用${cfg.key}
//                this.setMap(objectMap);
//            }
//        };
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/page/api/api.js.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return String.format("%s/%s.js", apiPath, entityNameInPackage);
//            }
//        });
//        focList.add(new FileOutConfig("/templates/page/store/store.js.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return String.format("%s/%s.js", storePath, entityNameInPackage);
//            }
//        });
//        focList.add(new FileOutConfig("/templates/page/view/index.vue.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return String.format("%s/%s.vue", viewPath, entityNameInPackage);
//            }
//        });
//        focList.add(new FileOutConfig("/templates/page/view/save.vue.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return String.format("%s/save-%s.vue", viewPath, entityNameInPackage);
//            }
//        });
//        focList.add(new FileOutConfig("/templates/page/view/update.vue.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return String.format("%s/update-%s.vue", viewPath, entityNameInPackage);
//            }
//        });
//        focList.add(new FileOutConfig("/templates/page/view/view.vue.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return String.format("%s/view-%s.vue", viewPath, entityNameInPackage);
//            }
//        });
//
//        injectionConfig.setFileOutConfigList(focList);
//
//        return injectionConfig;
//    }
//
//    /**
//     * 数据源配置
//     *
//     * @return
//     */
//    private static DataSourceConfig geDataSourceConfig() {
//
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/qd_admin");
//        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("aA@123456");
//
//        return dataSourceConfig;
//    }
//
//    /**
//     * 模板配置
//     *
//     * @return
//     */
//    private static TemplateConfig getTemplateConfig() {
//
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 禁用自带输出
//        templateConfig.setController("");
//        templateConfig.setService("");
//        templateConfig.setServiceImpl("");
//        templateConfig.setEntity("");
//        templateConfig.setMapper("");
//        templateConfig.setXml("");
//
//        return templateConfig;
//    }
//
//    private static StrategyConfig getStrategyConfig(String tableName, String tableNamePrefix) {
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setInclude(tableName);
//        strategyConfig.setTablePrefix(tableNamePrefix);
//        strategyConfig.setControllerMappingHyphenStyle(true);
//
//        return strategyConfig;
//    }
//}
