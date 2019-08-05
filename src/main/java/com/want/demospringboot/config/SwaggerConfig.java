package com.want.demospringboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.want.demospringboot.utils.CommonConstant;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		List<Parameter> parameterList = new ArrayList<>();
        parameterList
        	.add(new ParameterBuilder().name(CommonConstant.AUTH_HEADER)
        	.description("請輸入JWT")
        	.modelRef(new ModelRef("string"))
        	.parameterType("header")
        	.required(false)
        	.build());

		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.apiInfo())
//				.globalOperationParameters(parameterList)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.want.demospringboot.controller"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(Lists.newArrayList(apiKey()))
				;
	}

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Demo")
//                .description("rest api")
                .contact(new Contact("","",""))
                .version("1.0")
                .build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("apikey","Authorization", "header");
	}

}
