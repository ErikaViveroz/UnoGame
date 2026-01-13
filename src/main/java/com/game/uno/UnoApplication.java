package com.game.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@EnableJpaRepositories("com.game.uno.dao")
public class UnoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(UnoApplication.class)
                        .headless(false)
                        .run(args);

        EventQueue.invokeLater(() -> {
            try {
                Uno game = context.getBean(Uno.class);

                game.initUI(); // 👈 Swing aquí

                String player1 = game.validateName("Jugador 1");
                String player2 = game.validateName("Jugador 2");

                game.startGame(player1, player2);
                game.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}


