package com.lanny.spring.demo.converter.config;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Lanny Yao
 * @date 9/20/2018 11:07 AM
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    private static final Predicate<String> EXTERNAL_API_FILTERING_PREDICATE = path -> !path.contains("health") && !path
            .contains("tenantInfo") && !path.contains("legal");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lanny.spring.demo"))
                .paths(EXTERNAL_API_FILTERING_PREDICATE::test)
                .build()
                .host("localhost:8080")
                .pathProvider(new RelativePathProvider(null) {
                    @Override
                    public String getApplicationBasePath() {
                        return "";
                    }
                })
                .globalResponseMessage(RequestMethod.POST, buildDefaultSecurityResponseMessages())
                .globalResponseMessage(RequestMethod.GET, buildDefaultSecurityResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, buildDefaultSecurityResponseMessages())
                .globalResponseMessage(RequestMethod.PATCH, buildDefaultSecurityResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, buildDefaultSecurityResponseMessages())
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> buildDefaultSecurityResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message("Unauthorized")
                        .responseModel(new ModelRef("void")).build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .message("Forbidden")
                        .responseModel(new ModelRef("void")).build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Family API",
                "",
                "1.0",
                null,
                null,
                null,
                null,
                Arrays.asList(new StringVendorExtension("x-released", "no")));
    }
}
