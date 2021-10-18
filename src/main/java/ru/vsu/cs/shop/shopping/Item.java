package ru.vsu.cs.shop.shopping;

import ru.vsu.cs.shop.products.Good;

import java.math.BigDecimal;

/**
 * Данный класс представляет элемент корзины или склада, они имеют общее начало
 */

public class Item {
    private long ID;
    private Good good;
    private int amount;
    private String manufacturer;
    private String manufactureDate;
    private String expirationDate;

    public Item(long ID, Good good, int amount, String manufacturer, String manufactureDate, String expirationDate) {
        this.ID = ID;
        this.good = good;
        this.amount = amount;
        this.manufacturer = manufacturer;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

    public long getID() {
        return ID;
    }

    public Good getGoods() {
        return good;
    }

    public int getAmount() {
        return amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    //так как мы не будем изменять данные предметы, set-методы не обязательны
    public void setID(long ID) {
        this.ID = ID;
    }

    public void setGoods(Good goods) {
        this.good = goods;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getItemPrice() {
        return good.getPrice().multiply(BigDecimal.valueOf(amount));
    }

    public void shiftAmount(int amount) {
        this.amount += amount;
    }

    //следующие методы сравнивают "одинаковость" товаров
    public boolean isEqual(Item item) {
        return this.getGoods().isEqual(item.getGoods()) && checkParams(item);
    }

    private boolean checkParams(Item item) {
        if (this.manufacturer != item.manufacturer) return false;
        if (this.manufactureDate != item.manufactureDate) return false;
        if (this.expirationDate != item.expirationDate) return false;
        return true;
    }

    public Item shiftItemAmount(int delta, Item temp) { //меняет кол-во между предметами на дельту
        if (temp == null) {
            temp = new Item(ID, good, 0, manufacturer, manufactureDate, expirationDate);
        }
        delta = Math.max(delta, -temp.amount); //TODO хз-
        delta = Math.min(delta, amount);
        temp.shiftAmount(delta);
        shiftAmount(-delta);
        return temp;
    }

    public Item clone() {
        return new Item(ID, good, amount, manufacturer, manufactureDate, expirationDate);
    }
}
