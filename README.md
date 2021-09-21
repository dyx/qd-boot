
## 简介
- springboot+vue实现的管理系统脚手架，也是自己知识的一个梳理总结Demo
- 前端基于[iview-admin](https://gitee.com/icarusion/iview-admin)，后端基于[spring-boot](https://spring.io/projects/spring-boot)
- 遵循阿里巴巴Java编码规约
- 基于MyBatis-Plus，快速完成CURD
- 支持前端、后台基础增CRUD代码生成 [文档](https://gitee.com/luhaidongxl/qd-boot/wikis/%E7%94%9F%E6%88%90%E4%BB%A3%E7%A0%81?sort_id=1531592)
- 权限相关
    - 认证使用jwt token
    - 鉴权使用自定义filter(QdAuthorizationFilter)
    - 会话管理使用redis
    - 功能权限，基于RBAC，支持菜单、按钮级别控制
    - 数据权限 [文档](https://gitee.com/luhaidongxl/qd-boot/wikis/%E6%95%B0%E6%8D%AE%E6%9D%83%E9%99%90?sort_id=1544960)
- 其他 
    - aop配置事务、记录请求日志（记录地点、耗时信息）
    - 统一的异常处理
    - Swagger Api
    

## 主要功能    
- 用户管理，维护用户、分配角色
- 组织管理，维护公司、部门
- 角色管理，维护角色、分配功能权限
- 资源管理，资源维护
- 字典管理，字典维护，更新字典缓存

## 核心依赖
#### 前端
- vue 2.6.10
- vue-router 3.0.1
- vuex 3.1.1
- axios 0.19.0
- iview 3.4.2

#### 后端
- Spring Boot 2.1.6.RELEASE
- Mybatis Plus 3.1.2
- Mapstruct 1.3.0.Final
- Swagger 2.9.2

## 项目结构
```
qd-boot
├── qd-generator
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── lhd
│           │           └── qd
│           │               ├── PageGenerator.java 前端代码生成器
│           │               └── ServiceGenerator.java 后端代码生成器
│           └── resources
│               └── templates
│                   ├── page 前端模板
│                   └── service 后端模板
├── qd-page
│   └── src
│       ├── api 请求
│       │   └── module
│       │       └── sys 按模块存储
│       ├── assets 图片字体资源
│       ├── components
│       │   ├── charts 图表
│       │   ├── common 公共样式
│       │   ├── count-to 滚动数字
│       │   ├── customize 自定义模板组件
│       │   │   ├── qd-button 按钮
│       │   │   ├── qd-form 新建编辑窗口
│       │   │   ├── qd-loading 加载中
│       │   │   ├── qd-page 分页列表
│       │   │   ├── qd-ref 通用参照组件
│       │   │   ├── qd-tree-view 树详情
│       │   │   └── qd-view 抽屉详情
│       │   ├── info-card 信息卡
│       │   ├── login-form 登录组件
│       │   ├── main 主框架
│       │   ├── parent-view 多级菜单的父级组件
│       │   └── ref 自定义参照组件，如客户新增时选取所属人员
│       ├── config 初始配置
│       ├── directive 指令，鉴权、拖拽
│       ├── libs 工具类
│       ├── locale 多语言配置
│       ├── router 路由
│       ├── store 状态
│       │   └── module
│       │       └── sys 按模块存储
│       │           └── user
│       │               ├── save-user.vue 新建
│       │               ├── update-user.vue 编辑
│       │               ├── user.vue 首页
│       │               └── view-user.vue 详情
│       └── view 具体页面
└── qd-service
    ├── db
    ├── doc
    └── src
        └── main
            ├── java
            │   └── com
            │       └── lhd
            │           └── qd
            │               ├── aspect 请求日志
            │               ├── base 基础类
            │               ├── config 配置类
            │               ├── constant 常量类
            │               ├── exception 异常类
            │               ├── handler 各种处理类，鉴权、缓存字典、实体新增修改填充字段
            │               ├── module
            │               │   └── sys 按模块存储
            │               │       └── user
            │               │           ├── controller 控制层
            │               │           ├── dao 数据层
            │               │           ├── model
            │               │           │   ├── converter 实体转换器
            │               │           │   ├── dto 数据传输对象
            │               │           │   ├── entity 数据对象
            │               │           │   └── vo 视图对象
            │               │           └── service 业务逻辑层
            │               │               └── impl
            │               ├── tree 通用树结构            
            │               └── util 工具类
            └── resources
                ├── mapper mapper.xml
                │   └── sys
                ├── application.yml 系统配置
                └── logback.xml 日志配置
```

## 运行
- 环境准备
    - 开发工具：IDEA，需安装插件Lombok plugin、vue.js
    - 数据库：MySQL，创建数据库qd_admin
    - 缓存服务器：Redis
    - js运行环境：Node.js
- git下载代码
##### 后端
- 初始化数据，MySQL执行/qd-service/db/init.sql
- 修改配置，/qd-service/src/main/resources/application.yml
    ```
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/qd_admin
        username: root
        password: xxx
      redis:
        # 可选择0～15
        database: 15
        host: localhost
        port: 6379
        # 默认为空
        password:
    ```
- 运行QdApplication.java，则可启动后台项目
- 后端接口文档地址：http://localhost:20191/swagger-ui/

##### 前端
- cd qd-page
- yarn install
- yarn run dev
- 前端访问地址：http://localhost:20190

## 效果图
![Api管理](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/Api%E7%AE%A1%E7%90%86.png)

![用户管理](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86.png)

![组织管理](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E7%BB%84%E7%BB%87%E7%AE%A1%E7%90%86.png)

![角色管理](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.png)

![分配功能权限](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E5%88%86%E9%85%8D%E5%8A%9F%E8%83%BD%E6%9D%83%E9%99%90.png)

![分配数据权限](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E5%88%86%E9%85%8D%E6%95%B0%E6%8D%AE%E6%9D%83%E9%99%90.png)

![资源管理](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E8%B5%84%E6%BA%90%E7%AE%A1%E7%90%86.png)

![字典管理](https://gitee.com/luhaidongxl/qd-boot/raw/master/doc/img/%E5%AD%97%E5%85%B8%E7%AE%A1%E7%90%86.png)

## 常见问题
#### 启动时报如下错误
```
***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class
```
- mvn compile 或 IDEA工具 build project 
