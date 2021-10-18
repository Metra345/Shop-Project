package ru.vsu.cs.shop.products;

import java.math.BigDecimal;

/**
 * Данный класс представляет абстрактную модель любого продукта в магазине, который мы можеим продать как товар (краска, штукатурка, грунтовка)
 */

public abstract class Product extends Good {

    private String color;
    private double weight;
    private boolean isColoring;
    private String consumption;

    public Product(long ID, String name, String type, BigDecimal price, String color, double weight, boolean isColoring, String consumption) {
        super(ID, name, type, price);
        this.color = color;
        this.weight = weight;
        this.isColoring = isColoring;
        this.consumption = consumption;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isColoring() {
        return isColoring;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setColor(String color) {
        if (isColoring) {
            this.color = color;
        }
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setColoring(boolean coloring) {
        isColoring = coloring;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

}


