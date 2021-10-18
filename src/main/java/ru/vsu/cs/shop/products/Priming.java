package ru.vsu.cs.shop.products;

import java.math.BigDecimal;

/**
 * Данный класс представляет грунтовку, особенностью является концентрат (прим. Все грунтовки разводятся с водой в долевом соотношении с нею)
 */

public class Priming extends Product {

    private double concentrate;

    public Priming(long ID, String name, String type, BigDecimal price, String color, double weight, boolean isColoring, String consumption, double concentrate) {
        super(ID, name, type, price, color, weight, isColoring, consumption);
        this.concentrate = concentrate;
    }

    public double getConcentrate() {
        return concentrate;
    }

    public void setConcentrate(double concentrate) {
        this.concentrate = concentrate;
    }

}
