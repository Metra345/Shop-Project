package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.machines.ColoringMachine;
import ru.vsu.cs.shop.products.Paint;

import java.math.BigDecimal;

public class testCM {
    /**
     * Проверяем работу колеровочной машины
     */
    @Test
    public void testColoringMachine() {
        ColoringMachine machine = new ColoringMachine();
        Paint StoColor_Top = new Paint((long) 12, "StoColor Top", "Фасадная", new BigDecimal("12000.00"), "#000000", 15, true, "0.15-0.20", "Санирующая");
        machine.coloring(StoColor_Top, "#111111");
        Assert.assertEquals("#111111", StoColor_Top.getColor());
    }
}
