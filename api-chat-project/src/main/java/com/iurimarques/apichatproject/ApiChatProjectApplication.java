package com.iurimarques.apichatproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ApiChatProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiChatProjectApplication.class, args);
	}

}
