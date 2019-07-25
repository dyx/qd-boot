package com.lhd.qd.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.lhd.qd.handler.LogicDeleteByIdsWithFill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author lhd
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * sql注入器
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {

        return new DefaultSqlInjector() {
            /**
             * 注入自定义全局方法
             */
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass);
                methodList.add(new LogicDeleteByIdWithFill());
                methodList.add(new LogicDeleteByIdsWithFill());
                return methodList;
            }
        };
    }
}