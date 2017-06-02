package com.mtch.hmgmt.application.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// @EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class SwaggerConfig {

	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mtch.hmgmt.controller")).paths(regex("/room.*")).build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Hotel Management API")
				.description("Hotel Management API reference for developers").termsOfServiceUrl("http://itechgenie.com")
				.contact("itechgenie@gmail.com").license("Hotel Management License").licenseUrl("itechgenie@gmail.com")
				.version("1.0").build();
	}
}
