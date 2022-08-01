package com.mediams.challenge.ordermanagement;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OrdermanagementApplication implements WebMvcConfigurer
{

	@Autowired private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(OrdermanagementApplication.class, args);
	}


}
