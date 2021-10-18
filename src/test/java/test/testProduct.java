package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.products.Paint;

import java.math.BigDecimal;

public class testProduct {
    /**
     * Проверяем создание продукта и соответсвия данных которые мы зададим этому продукту
     */
    @Test
    public void testProduct() {
        Paint StoColor_Top = new Paint((long) 12, "StoColor Top", "Фасадная", new BigDecimal("12000.00"), "#FFF700", 15, true, "0.15-0.20", "Санирующая");
        Assert.assertEquals("StoColor Top", StoColor_Top.getName());
        Assert.assertEquals("Фасадная", StoColor_Top.getType());
        Assert.assertEquals("Санирующая", StoColor_Top.getPaintClass());
        Assert.assertTrue(StoColor_Top.isColoring());
        Assert.assertEquals("#FFF700", StoColor_Top.getColor());
    }
}
