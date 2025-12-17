package com.game.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UnoGameApplication {

    public static void main(String[] args) {
		SpringApplication.run(UnoGameApplication.class, args);
	}

}
