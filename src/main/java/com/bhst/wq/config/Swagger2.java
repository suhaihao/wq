package com.bhst.wq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.serverUrl}")
    private String serverUrl;

    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     */
    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<Parameter>();

        ParameterBuilder x_token = new ParameterBuilder();
        x_token.name("x_token").description("登录校验")//name表示名称，description表示描述
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("").build();//required表示是否必填，defaultvalue表示默认值
        pars.add(x_token.build());//添加完此处一定要把下边的带***的也加上否则不生效

        ParameterBuilder username = new ParameterBuilder();
        username.name("username").description("登录校验")//name表示名称，description表示描述
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("").build();//required表示是否必填，defaultvalue表示默认值
        pars.add(username.build());//添加完此处一定要把下边的带***的也加上否则不生效

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bhst.wq"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl(serverUrl)
                .description(title)
                .version(version)
                .description("API 描述")
                .build();
    }
}