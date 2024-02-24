package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

@Getter
public class Car extends Product {
    private String color;

    public void setColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color;
    }
}
