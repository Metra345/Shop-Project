package test;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.shop.people.Client;
import ru.vsu.cs.shop.shopping.Shop;

import java.math.BigDecimal;

public class testClient {
    /**
     * Проверяем создание клиента
     */
    @Test
    public void testClient() {
        Client client = new Client((long) 123, "Деров Василий", "Воронеж, Пушкинская 2");
        Assert.assertEquals(java.util.Optional.of((long) 123), java.util.Optional.ofNullable(client.getID()));
        Assert.assertEquals("Деров Василий", client.getName());
        Assert.assertEquals("Воронеж, Пушкинская 2", client.getDeliveryAddress());
    }
}
