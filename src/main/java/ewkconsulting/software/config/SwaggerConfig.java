package ewkconsulting.software.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SPRING_WEB)
                .select()                 
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "REST API - Marjo's C", 
	      "This API Exposes Endpoints for uploading CVs that represent car inventory and deal jackets", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Damond Howard", "https://lastminutehair.com/", "networkninjadh@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
}