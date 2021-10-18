package ru.vsu.cs.shop.products;

import java.math.BigDecimal;

/**
 * Данный класс представляет краску, особенностью является тип краски (прим. Область её применения)
 */

public class Paint extends Product {

    private String paintClass;

    public Paint(long ID, String name, String type, BigDecimal price, String color, double weight, boolean isColoring, String consumption, String paintClass) {
        super(ID, name, type, price, color, weight, isColoring, consumption);
        this.paintClass = paintClass;
    }

    public String getPaintClass() {
        return paintClass;
    }

    public void setPaintClass(String paintClass) {
        this.paintClass = paintClass;
    }

}


