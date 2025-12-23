package com.game.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.awt.*;
@EnableJpaRepositories("com.game.uno.dao")
@SpringBootApplication
public class UnoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(UnoApplication.class, args);

        // Look & Feel (sigue siendo Swing, no hay problema)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                Uno game = context.getBean(Uno.class);
                game.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

