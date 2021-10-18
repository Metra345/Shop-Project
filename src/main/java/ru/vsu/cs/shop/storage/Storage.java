package ru.vsu.cs.shop.storage;

import ru.vsu.cs.shop.database.DataStorage;
import ru.vsu.cs.shop.shopping.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Данный класс представляет склад (на будущее при реализации БД), аналогично корзине
 */

public class Storage {

    private List<Item> storageList;
    private long ID;

    public Storage(long ID) {
        this.storageList = new ArrayList<>();
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void add(Item storageItem) {

        List<Item> storageItems = storageList.stream()
                .filter(c -> (c.getGoods().getID()) == (storageItem.getGoods().getID()))
                .collect(Collectors.toList());
        if (storageItems.size() == 1) {
            Item item = storageItems.get(0);
            item.setAmount(item.getAmount() + storageItem.getAmount());
        } else if (storageItems.size() == 0) {
            storageList.add(storageItem);
        } else {
            throw new IllegalStateException("Ошибка!");
        }
    }

    public boolean remove(Object o) {
        return storageList.remove(o);
    }

    public boolean removeAll(Collection<?> c) {
        return storageList.removeAll(c);
    }

    public void printStorage() {
        int i = 1;
        for (Item storageItem :
                storageList) {
            System.out.println((i++) + ". " + storageItem.getGoods().getName() + " x" + storageItem.getAmount() + " - " + storageItem.getGoods().getPrice() + "₽");
        }
    }

    //метод который проверяет достаточно ли товара на складе для продажи
    public int getAmountOfGood(Item item) {
        //sItem = Storage Item
        for (Item sItem :
                storageList) {
            if (sItem.isEqual(item)) return sItem.getAmount();
        }
        return 0;
    }

    public Item findItem(Item item) {
        //sItem = Storage Item
        for (Item sItem :
                storageList) {
            if (sItem.isEqual(item)) return sItem;
        }
        return null;
    }

    public void loadFromDB() {
        DataStorage ds = new DataStorage();
        storageList = ds.getAllItems();
    }

    public List<Item> getStorageList() {
        return storageList;
    }

    //быть или не быть
    public Item getItemByID(int ID) {
        for (Item item :
                storageList) {
            if (item.getID() == ID) return item;
        }
        return null;
    }

    public Storage clone() {
        Storage temp = new Storage(ID);
        for (Item item :
                this.getStorageList()) {
            temp.storageList.add(item.clone());
        }
        return temp;
    }

    public void clear() {
        ArrayList<Item> temp = new ArrayList<>();
        for (Item item :
                this.getStorageList()) {
            temp.add(item);
        }
        for (Item item :
                temp) {
            if(item.getAmount() == 0) remove(item);
        }
    }
}
