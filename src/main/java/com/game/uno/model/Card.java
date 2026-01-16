package com.game.uno.model;

import javax.swing.*;
import java.awt.*;

public class Card {

    private String color;   // am, az, ve, ro
    private String number;  // 1 - 9
    private String imagePath;

    public Card(String color, String number, String route) {
        this.color = color;
        this.number = number;
        this.imagePath = route + color + number + ".jpg";
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ImageIcon getScaledIcon(int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        return new ImageIcon(
                icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)
        );
    }
}
