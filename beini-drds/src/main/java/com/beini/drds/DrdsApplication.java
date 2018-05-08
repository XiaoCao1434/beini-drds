package com.beini.drds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * beini-drds启动类
 * 
 * @author lb_chen
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.beini")
@ImportResource(locations = { "classpath:druid-spring.xml" })
public class DrdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrdsApplication.class, args);
	}
}
