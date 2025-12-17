package com.game.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UnoGameApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UnoGameApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
	}

}
