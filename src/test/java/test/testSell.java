package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.database.DataStorage;
import ru.vsu.cs.shop.products.Good;
import ru.vsu.cs.shop.products.Ware;
import ru.vsu.cs.shop.shopping.Cart;
import ru.vsu.cs.shop.shopping.Item;
import ru.vsu.cs.shop.shopping.Shop;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class testSell {
    /**
     * Проверяем продажу
     */
    @Test
    public void testSell() {
        Shop shop = new Shop("ПроСто Краски", "Урицкого 128", "89200000000", "10:00-18:00", BigDecimal.ZERO, 1);
        Good brush = new Ware((long) 223, "Кисть большая", "Кисти", new BigDecimal("450.00"));
        Item item = new Item(222, brush, 2, "Россия", "02.03.2019", "не ограничен");
        shop.getStorage().add(item);
        Cart cart = new Cart((long) 123);
        Assert.assertEquals(2, shop.getStorage().getAmountOfGood(item));
        Assert.assertEquals(BigDecimal.ZERO, shop.getCash());
        cart.add(item);
        BigDecimal waitingMoney = item.getGoods().getPrice().multiply(BigDecimal.valueOf(shop.getStorage().getAmountOfGood(item)));
        shop.sell(cart);
        Assert.assertEquals(waitingMoney, shop.getCash());
        Assert.assertEquals(0, shop.getStorage().getAmountOfGood(item));
        DataStorage ds = new DataStorage();
        Assert.assertEquals(222, ds.getAllSoldItems().stream().filter(c -> (c.getID()) == (item.getID())).collect(Collectors.toList()).get(0).getID());
    }
}
