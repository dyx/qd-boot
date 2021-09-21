package com.lhd.qd.config;

import com.lhd.qd.constant.CommonConsts;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * api文档配置
 * @author lhd
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(
                new ApiKey(CommonConsts.REQUEST_HEADER_KEY_AUTH, CommonConsts.REQUEST_HEADER_KEY_AUTH, "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(authorizationScope())
                        .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
    }

    private List<SecurityReference> authorizationScope() {

        return Collections.singletonList(
                new SecurityReference(CommonConsts.REQUEST_HEADER_KEY_AUTH,
                        new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")}));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("QD系统RESTful API")
                .version("1.0")
                .build();
    }

}
