package ru.vsu.cs.shop.products;

import java.math.BigDecimal;

/**
 * Данный класс представляет абстрактную модель любого товара который мы можем продавать в магазине
 */

public abstract class Good {

    private long ID;
    private String name;
    private String type;
    private BigDecimal price;

    public Good(long ID, String name, String type, BigDecimal price) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //проверяем может быть у нас уже есть такой товар?
    public boolean isEqual(Good good) {
        if(good.getID() != this.ID) return false;
        if(good.getName() != this.name) return false;
        if(good.getType() != this.type) return false;
        if(good.getPrice() != this.price) return false;
        return true;
    }

}
