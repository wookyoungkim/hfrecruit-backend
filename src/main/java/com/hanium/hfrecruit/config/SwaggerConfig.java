package com.hanium.hfrecruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Swagger 설정을 정의한 코드입니다.
 * .consume()과 .produces()는 각각 Request Content-Type, Response Content-Type에 대한 설정입니다.(선택)
 * .apiInfo()는 Swagger API 문서에 대한 설명을 표기하는 메소드입니다. (선택)
 * .apis()는 Swagger API 문서로 만들기 원하는 basePackage 경로입니다. (필수)
 * .path()는 URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger API 문서로 만듭니다.(필수)
 * @author MW
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hanium.hfrecruit.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("hf-recruit API")
                .description("hf-recruit API")
                .version("1.0")
                .build();
    }
}
