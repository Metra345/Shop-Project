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
import java.util.ArrayList;
import java.util.stream.Collectors;

public class testDBSavingItemSold {
    /**
     * Проверяем сохранение товара в БД (Sold_List)
     */
    @Test
    public void testDBSavingItemSold() {
        DataStorage ds = new DataStorage();
        Good brush = new Ware((long) 123, "Кистя большая", "Кисти", new BigDecimal("450.00"));
        Item item = new Item((long) 123, brush, 3, "UnitTest", "UnitTest", "UnitTest");
        Cart cart = new Cart((long) 123);
        ds.saveStorageItemToSold(item, cart.getID());
        ArrayList<Item> list = ds.getAllItems();
        /* украл из cart
        ArrayList<Item> collect = new ArrayList<>();
        for(Item c:list) {
            if(c.getID() == item.getID()) collect.add(c);
        }
        */
        Assert.assertEquals(123, list.stream().filter(c -> (c.getID()) == (item.getID())).collect(Collectors.toList()).get(0).getID());
    }
}
