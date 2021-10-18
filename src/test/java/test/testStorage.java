package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.products.Good;
import ru.vsu.cs.shop.products.Ware;
import ru.vsu.cs.shop.shopping.Item;
import ru.vsu.cs.shop.storage.Storage;

import java.math.BigDecimal;

public class testStorage {
    /**
     * Проверяем создание склада, "складируем" товар, и выводим что сейчас у нас на складе
     */
    @Test
    public void testStorage() {
        Storage storage = new Storage((long) 2);
        Good brush = new Ware((long) 223, "Кисть большая", "Кисти", new BigDecimal("450.00"));
        Item item = new Item(1, brush, 3, "Россия", "02.03.2019", "не ограничен");
        storage.add(item);
        Assert.assertEquals(3, storage.getAmountOfGood(item));
        storage.remove(item);
        Assert.assertEquals(0, storage.getAmountOfGood(item));
    }
}
