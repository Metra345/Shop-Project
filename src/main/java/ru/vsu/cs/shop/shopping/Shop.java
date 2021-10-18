package ru.vsu.cs.shop.shopping;

import ru.vsu.cs.shop.database.DataStorage;
import ru.vsu.cs.shop.storage.Storage;

import java.math.BigDecimal;

/**
 * Данный класс представляет магазин
 */

public class Shop {
    private String shopName;
    private String address;
    private String phone;
    private String workingTime;
    private BigDecimal cash;//protected?
    private Storage storage;

    public Shop(String shopName, String address, String phone, String workingTime, BigDecimal cash, int storageID) {
        this.shopName = shopName;
        this.address = address;
        this.phone = phone;
        this.workingTime = workingTime;
        this.cash = cash;
        storage = new Storage(storageID);
        storage.loadFromDB();
    }

    public Storage getStorage() {
        return storage;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public String getShopName() {
        return shopName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public void printShop() {
        System.out.println("Вас приветствует магазин: " + shopName + " по адресу " + address + " " + phone + " " + workingTime);
    }


    /**
     * весь метод sell работает (главное надежность) ради праивльного взаимодействия с БД
     * если с БД происходит какая-то проблема, то мы ловим внутренний exception
     * и соответственно не производим продажу (даже не смотря на фактическое наличие товара),
     * потому что мы не сможем потом зарегистрировать эту продажу и у нас будет потеря товара
     * принцип работы метода sell:
     * ->начальная логика метода
     * ->взаимодействие с БД! (100, 104)
     * ->дальше принимается решение уменьшать товар и завершать продажу
     * ->или же мы выбрасываем exception БД и отменяем продажу
     */

    //метод продажи
    public boolean sell(Cart cart) {
        int amount;
        Item tmp; //временный товар(текущий для продажи)
        boolean enough = true;
        DataStorage ds = new DataStorage();

        for (Item item : //проходит по товарам в корзине и ищет товары на складе
                cart.getGoodsList()) {
            if (item.getAmount() > 0) {
                tmp = storage.findItem(item);
                if (tmp != null) { //нашел или нет
                    amount = tmp.getAmount();
                    if (amount >= item.getAmount()) { //нашел, если достаточно, то продал
                        ds.itemSelling(tmp, item.getAmount(), storage.getID());//взаимодействие с БД
                        cash = cash.add(item.getItemPrice());
                        tmp.shiftAmount(-item.getAmount());
                    } else { //нашел, но недостаточно, то продал все что было на складе
                        ds.itemSelling(tmp, amount, storage.getID());//взаимодействие с БД
                        cash = cash.add(tmp.getItemPrice());
                        tmp.setAmount(0);
                        enough = false;
                    }
                } else { //не нашел, не продал и расстроился
                    enough = false;
                }
            }
        }
        return enough;
    }
}
