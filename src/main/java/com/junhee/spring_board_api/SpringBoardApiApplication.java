package com.junhee.spring_board_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBoardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoardApiApplication.class, args);
	}

}
