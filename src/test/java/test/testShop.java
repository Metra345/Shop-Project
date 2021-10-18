package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.shopping.Shop;

import java.math.BigDecimal;

public class testShop {
    /**
     * Проверяем создание магазина
     */
    @Test
    public void testShop() {
        Shop shop = new Shop("ПроСто Краски", "Урицкого 128", "89200000000", "10:00-18:00", BigDecimal.ZERO, 1);
        Assert.assertEquals("ПроСто Краски", shop.getShopName());
        Assert.assertEquals("Урицкого 128", shop.getAddress());
        Assert.assertEquals("89200000000", shop.getPhone());
        Assert.assertEquals("10:00-18:00", shop.getWorkingTime());
    }
}
