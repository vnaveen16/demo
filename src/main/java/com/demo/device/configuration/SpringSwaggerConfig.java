package com.demo.device.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringSwaggerConfig {

	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
	 * .build(); }
	 */
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.device.configuration"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "DeviceUserAgentApplication",
                "An application to create, read, delete, update device user agent details  from a device repository by device id",
                "DeviceUserAgentApplication v1",
                "Terms of service",
                "test@gmail.com",
                "License of API",
                "https://swagger.io/docs/");
        return apiInfo;
    }
    
	/*
	 * @Bean public DeviceDemoParser createParser() {
	 * 
	 * return new DeviceDemoParser(); }
	 */
    
    
	/*
	 * @Bean(name="entityManagerFactory") public LocalSessionFactoryBean
	 * sessionFactory() { LocalSessionFactoryBean sessionFactory = new
	 * LocalSessionFactoryBean();
	 * 
	 * return sessionFactory; }
	 */
	 
}
