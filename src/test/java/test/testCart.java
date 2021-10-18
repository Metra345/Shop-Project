package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.products.Good;
import ru.vsu.cs.shop.products.Priming;
import ru.vsu.cs.shop.products.Product;
import ru.vsu.cs.shop.products.Ware;
import ru.vsu.cs.shop.shopping.Cart;
import ru.vsu.cs.shop.shopping.Item;

import java.math.BigDecimal;

public class testCart {
    /**
     * Проверяем создание корзины, создаём товары которые мы положим в корзину, и затем выведем содержимое и вычисленную итоговую цену
     */
    @Test
    public void testCart() {
        Cart cart1 = new Cart((long) 123);
        Product product1 = new Priming((long) 13, "StoPrim Solid", "Регулирующая", new BigDecimal("1590.90"), "#100100", 10, false, "0.1-0.2 l/m^2", 0.25);
        Good brush = new Ware((long) 223, "Кисть большая", "Кисти", new BigDecimal("450.00"));
        Item item1 = new Item(1, product1, 12, "Россия", "10.10.2020", "11.10.2021");
        Item item2 = new Item(2, brush, 2, "Россия", "02.03.2019", "не ограничен");
        cart1.add(item1);
        cart1.add(item2);
        Assert.assertEquals((item1.getGoods().getPrice().multiply(BigDecimal.valueOf(item1.getAmount()))), item1.getItemPrice());
        Assert.assertEquals((item2.getGoods().getPrice().multiply(BigDecimal.valueOf(item2.getAmount()))), item2.getItemPrice());
        Assert.assertEquals(item1, cart1.getGoodsList().get(0));
        Assert.assertEquals(item2, cart1.getGoodsList().get(1));
        Assert.assertEquals(((item1.getGoods().getPrice().multiply(BigDecimal.valueOf(item1.getAmount()))).add((item2.getGoods().getPrice().multiply(BigDecimal.valueOf(item2.getAmount()))))), cart1.getTotalPrice());
    }
}
