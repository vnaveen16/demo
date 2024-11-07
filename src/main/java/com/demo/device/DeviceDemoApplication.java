package com.demo.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.device","com.demo.device.service","com.demo.device.repository","com.demo.device.dao"})
//@EntityScan("com.demo.device.entity")
@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages={"com.demo.device.repository"})
public class DeviceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceDemoApplication.class, args);
	}

}
