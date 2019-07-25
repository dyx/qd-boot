package com.lhd.qd.config;

import com.lhd.qd.util.JacksonUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web基础配置
 *
 * @author lhd
 */
@Configuration
public class JacksonConfig {

    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return (builder) -> JacksonUtils.SERIALIZER_MAP.forEach(builder::serializerByType);
    }
}
