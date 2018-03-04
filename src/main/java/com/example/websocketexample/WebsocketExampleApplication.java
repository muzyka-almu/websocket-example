package com.example.websocketexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.example.websocketexample" })
public class WebsocketExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketExampleApplication.class, args);
	}
}
