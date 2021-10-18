package ru.vsu.cs.shop.machines;

import ru.vsu.cs.shop.products.Product;

/**
 * Данный класс представляет реализацию работы колеровочной машины, она берёт продукт и добавляетв него в цвет (прим. Все продукты по умолчанию белые)
 */

public class ColoringMachine {
    //private int flaskAmount;
    //private String flaskColor;

    public ColoringMachine() {

    }

    public void coloring(Product product, String color) {
        product.setColor(color);
    }
    //как принимать продукт и запихивать его сюда?

}

