package ru.vsu.cs.shop.shopping;

import ru.vsu.cs.shop.storage.Storable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Данный класс представляет корзину
 */

public class Cart implements Storable {

    private List<Item> goodsList;

    private long ID;

    public Cart(long ID) {
        this.goodsList = new ArrayList<>();
        this.ID = ID;
    }

    public List<Item> getGoodsList() {
        return goodsList;
    }

    public long getID() {
        return ID;
    }

    public void setId(long ID) {
        this.ID = ID;
    }

    public void add(Item cartItem) {

        List<Item> items = goodsList.stream()
                .filter(c -> (c.getGoods().getID()) == (cartItem.getGoods().getID()))
                .collect(Collectors.toList());

        if (items.size() == 1) {
            Item item = items.get(0);
            item.setAmount(item.getAmount() + cartItem.getAmount());
        } else if (items.size() == 0) {
            goodsList.add(cartItem);
        } else {
            throw new IllegalStateException("Ошибка!");
        }
    }

    public boolean remove(Object o) {
        return goodsList.remove(o);
    }

    public boolean removeAll(Collection<?> c) {
        return goodsList.removeAll(c);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (Item c : goodsList) {
            total = total.add(c.getItemPrice());
        }
        return total;
    }

    public void printCart() {
        //System.out.println("Cart ID: " + getID());
        int i = 1;
        for (Item item :
                goodsList) {
            System.out.println((i++) + ". " + item.getGoods().getName() + " x" + item.getAmount() + " = " + item.getItemPrice());
        }
        System.out.println("Итого: " + getTotalPrice() + "₽");
    }
}
