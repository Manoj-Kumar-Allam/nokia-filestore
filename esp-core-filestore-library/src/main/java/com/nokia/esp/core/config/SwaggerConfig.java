package com.nokia.esp.core.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * The Swagger Configuration Class
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/** The Constant BASE_PACKAGE. */
	private static final String BASE_PACKAGE = "com.nokia";
	
	/**
	 * Contact Information
	 */
	public static final Contact DEFAULT_CONTACT = new Contact("Manoj Kumar Allam", "https://github.com/Manoj-Kumar-Allam", "amanoj1308@outlook.com");

	/**
	 * API info
	 */
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Nokia File Store", "Nokia File Store", "1.0.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

	/**
	 * Produces and Consumes Info
	 */
	private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS = new HashSet<>(Arrays.asList("application/json"));

	/**
	 * The Swagger API Call
	 * 
	 * @return the docker
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCERS_AND_CONSUMERS).consumes(DEFAULT_PRODUCERS_AND_CONSUMERS).select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)).paths(PathSelectors.any()).build();
	}
}
