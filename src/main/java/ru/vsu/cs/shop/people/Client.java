package ru.vsu.cs.shop.people;

import ru.vsu.cs.shop.storage.Storable;

/**
 * Данный класс представляет нам клиента и возможное взаимодействие с ним
 */

public class Client implements Storable {

    private long ID;
    private String name;
    private String deliveryAddress;

    public Client(long ID, String name, String deliveryAddress) {
        this.ID = ID;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setId(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void printClient() {
        System.out.println("ID: " + ID + " " + "Меня зовут " + name + " доставтье мне по адресу: " + deliveryAddress);
    }

}



