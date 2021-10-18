package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.database.DataStorage;
import ru.vsu.cs.shop.products.Good;
import ru.vsu.cs.shop.products.Priming;
import ru.vsu.cs.shop.products.Product;
import ru.vsu.cs.shop.products.Ware;
import ru.vsu.cs.shop.shopping.Cart;
import ru.vsu.cs.shop.shopping.Item;

import java.math.BigDecimal;

public class testDBSavingGood {
    /**
     * Проверяем сохранение товара в БД (Catalog)
     */
    @Test
    public void testDBSavingGood() {
        DataStorage ds = new DataStorage();
        Good product = new Priming((long) 13, "StoPrim Solid - UnitTestObject", "Регулирующая", new BigDecimal("1590.90"), "#00FF00", 10, false, "0.1-0.2 l/m^2", 0.25);
        ds.saveGoodToDb(product);
        Assert.assertEquals("StoPrim Solid - UnitTestObject", ds.getAllGoods().get((int) (product.getID())).getName());
    }
}
