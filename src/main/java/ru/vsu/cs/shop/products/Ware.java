package ru.vsu.cs.shop.products;

import java.math.BigDecimal;

/**
 * Данный класс представляет любого товара, что не является продуктом
 */

public class Ware extends Good {
    public Ware(long ID, String name, String type, BigDecimal price) {
        super(ID, name, type, price);
    }
}
