package com.matheusoliveira04.picpaysimplificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PicpaysimplificadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpaysimplificadoApplication.class, args);
	}

}
