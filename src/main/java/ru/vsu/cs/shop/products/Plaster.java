package ru.vsu.cs.shop.products;

import java.math.BigDecimal;

/**
 * Данный класс представляет штукатурку, особенностью является размер зерна в составе, которое создаёт фактуру
 */

public class Plaster extends Product {

    private double grainSize;

    public Plaster(long ID, String name, String type, BigDecimal price, String color, double weight, boolean isColoring, String consumption, double grainSize) {
        super(ID, name, type, price, color, weight, isColoring, consumption);
        this.grainSize = grainSize;
    }

    public double getGrainSize() {
        return grainSize;
    }

    public void setGrainSize(double grainSize) {
        this.grainSize = grainSize;
    }

}
