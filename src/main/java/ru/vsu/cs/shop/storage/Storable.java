package ru.vsu.cs.shop.storage;

/**
 * Данный интерфейс представляет будущее взаимодействие с БД
 */

public interface Storable {

    long getID();

    void setId(long ID);

}
