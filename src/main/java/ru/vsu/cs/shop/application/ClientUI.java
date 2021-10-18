package ru.vsu.cs.shop.application;

import ru.vsu.cs.shop.shopping.Cart;
import ru.vsu.cs.shop.shopping.Item;
import ru.vsu.cs.shop.shopping.Shop;
import ru.vsu.cs.shop.storage.Storage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ClientUI {
    private Shop shop;
    private Storage storage;
    private Cart cart;
    private List<Item> items;

    public static void main(String[] args) {
        ClientUI client = new ClientUI();
        while (client.shoppingSession()) ;
    }

    public boolean shoppingSession() {
        shop = new Shop("ПроСто Краски", "Урицкого 128", "89200000000", "10:00-18:00", BigDecimal.ZERO, 1);
        System.out.println("Вас приветствует магазин " + shop.getShopName() + "\n");

        do {
            storage = shop.getStorage().clone();
            storage.clear();
            cart = new Cart(123456);
            items = storage.getStorageList();
        } while (cartActions());

        shop.sell(cart);
        System.out.println("Продажа прошла успешно");
        cart.printCart();

        return inputInt(0, 1, "Новая корзина - 1\nВыйти - 0\n") == 1;
    }

    public boolean cartActions() {
        Item temp;
        storage.printStorage();
        System.out.println();
        while (true) {

            switch (inputInt(0, 6,
                    "Добавить ещё предмет - 1\n" +
                            "Посмотреть корзину - 2\n" +
                            "Каталог - 3\n" +
                            "Удалить предмет - 4\n" +
                            "Изменить кол-во - 5\n" +
                            "Очистить корзину - 6\n" +
                            "Завершить покупки - 0\n" +
                            "Выберите действие: ")) {
                case 1: {
                    Item stItem = items.get(inputInt(1, items.size(), "Выберите предмет: ") - 1);
                    temp = stItem.shiftItemAmount(inputInt(0, stItem.getAmount(), "Введите количество (максимум " + stItem.getAmount() + "): "), null);
                    if (temp.getAmount() > 0) {
                        cart.add(temp);
                    }
                }
                break;
                case 2: {
                    if (cart.getGoodsList().size() > 0) {
                        System.out.println("Ваша корзина: ");
                        cart.printCart();
                        System.out.println();
                    } else System.out.println("Ваша корзина пуста!\n");
                }
                break;
                case 3: {
                    System.out.println("Каталог: ");
                    storage.printStorage();
                    System.out.println();
                }
                break;
                case 4: {
                    if (cart.getGoodsList().size() == 0) break;
                    cart.printCart();
                    Item cartItem = cart.getGoodsList().get(inputInt(1, cart.getGoodsList().size(), "Выберите предмет для удаления: ") - 1);
                    cart.remove(cartItem);
                    temp = storage.findItem(cartItem);
                    if (temp == null) {
                        storage.add(cartItem.clone());
                    } else temp.shiftAmount(cartItem.getAmount());
                }
                break;
                case 5: { //можно вставить в case 4, но не спрашивать кол-во
                    if (cart.getGoodsList().size() == 0) break;
                    cart.printCart();
                    Item cartItem = cart.getGoodsList().get(inputInt(1, cart.getGoodsList().size(), "Выберите предмет для изменения количества: ") - 1);
                    temp = storage.findItem(cartItem);
                    int sum = cartItem.getAmount();
                    if (temp != null) sum += temp.getAmount();
                    int delta = cartItem.getAmount() - inputInt(0, sum, "Введите количество (максимум " + sum + "): ");
                    Item item = cartItem.shiftItemAmount(delta, temp);
                    if (temp == null) storage.add(item);
                    if (cartItem.getAmount() == 0) cart.remove(cartItem);
                }
                break;
                case 6: {
                    return true;
                }
                default:
                    return false;
            }
            storage.clear();
        }
    }

    private int inputInt(int min, int max, String out) {
        int num;
        Scanner scn = new Scanner(System.in);
        do {
            System.out.print(out);
            try {
                num = Integer.parseInt(scn.nextLine());
            } catch (Exception e) {
                num = min - 1;
            }
            System.out.println();
        } while (num < min || num > max);
        return num;
    }
}
