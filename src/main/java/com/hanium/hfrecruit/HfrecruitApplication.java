package com.hanium.hfrecruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HfrecruitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HfrecruitApplication.class, args);
	}

}
