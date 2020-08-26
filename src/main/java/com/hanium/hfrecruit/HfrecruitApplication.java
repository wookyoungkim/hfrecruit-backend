package com.hanium.hfrecruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HfrecruitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HfrecruitApplication.class, args);
	}

    @SpringBootApplication
    public static class OAuth2Application {
        public static void main(String[] args){
            SpringApplication.run(OAuth2Application.class, args);
        }
    }
}
