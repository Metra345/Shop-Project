package ru.vsu.cs.shop.database;

import ru.vsu.cs.shop.products.*;
import ru.vsu.cs.shop.shopping.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStorage {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }

    public Good saveGoodToDb(Good good) {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO CATALOG (ID, Name, Good_Type, Price, Color, Weight, isColoring, Consumption, Paint_Class, Grain_Size, Concentrate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setLong(1, good.getID());
            statement.setString(2, good.getName());
            statement.setString(3, good.getType());
            statement.setDouble(4, good.getPrice().doubleValue());

            if (good instanceof Product) {
                Product product = (Product) good;
                statement.setString(5, product.getColor());
                statement.setDouble(6, product.getWeight());
                statement.setBoolean(7, product.isColoring());
                statement.setString(8, product.getConsumption());
                statement.setNull(9, Types.VARCHAR);
                statement.setNull(10, Types.DOUBLE);
                statement.setNull(11, Types.DOUBLE);

                if (good instanceof Paint) {
                    statement.setString(9, ((Paint) good).getPaintClass());
                }
                if (good instanceof Plaster) {
                    statement.setDouble(10, ((Plaster) good).getGrainSize());
                }
                if (good instanceof Priming) {
                    statement.setDouble(11, ((Priming) good).getConcentrate());
                }
                if (good instanceof Ware) {
                    statement.setNull(5, Types.VARCHAR);
                    statement.setNull(6, Types.DOUBLE);
                    statement.setNull(7, Types.BOOLEAN);
                    statement.setNull(8, Types.VARCHAR);
                    statement.setNull(9, Types.VARCHAR);
                    statement.setNull(10, Types.DOUBLE);
                    statement.setNull(11, Types.DOUBLE);
                }
            }
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot save Good/Bad!", exception);
        }
        return good;
    }

    public Item saveStorageItemToDB(Item item, long Stock_ID) {

        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO STORAGE_LIST (ID, Good_ID, Amount, Manufacturer, Manufacture_Date, Expiration_Date, Stock_ID) VALUES (?, ?, ?, ?, ?, ?, ?);");

            statement.setLong(1, item.getID());
            statement.setLong(2, item.getGoods().getID());
            statement.setInt(3, item.getAmount());
            statement.setString(4, item.getManufacturer());
            statement.setString(5, item.getManufactureDate());
            statement.setString(6, item.getExpirationDate());
            statement.setLong(7, Stock_ID); //в какой корзине(складе) лежит данный предмет

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot save Item!", exception);
        }
        return item;
    }

    public Item saveStorageItemToSold(Item item, long Stock_ID) {

        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO SOLD_LIST (ID, Good_ID, Amount, Manufacturer, Manufacture_Date, Expiration_Date, Stock_ID) VALUES (?, ?, ?, ?, ?, ?, ?);");

            statement.setLong(1, item.getID());
            statement.setLong(2, item.getGoods().getID());
            statement.setInt(3, item.getAmount());
            statement.setString(4, item.getManufacturer());
            statement.setString(5, item.getManufactureDate());
            statement.setString(6, item.getExpirationDate());
            statement.setLong(7, Stock_ID); //в какой корзине(складе) лежит данный предмет

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot save Item!", exception);
        }
        return item;
    }

    public void itemSelling(Item item, int amount, long Stock_ID) {//меняет записи в БД(а именно что продан)
        try (Connection c = getConnection()) {
            //этот запрос уменьшает кол-во предмета в БД
            PreparedStatement statement = c.prepareStatement("UPDATE STORAGE_LIST SET AMOUNT = " + (item.getAmount() - amount) + " WHERE ID = " + item.getID());
            statement.executeUpdate();
            //сохраняем в таблицу проданных предметов этот предмет
            saveStorageItemToSold(item, Stock_ID);
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot change smth!", exception);
        }
    }

    //метод можно расширить для работы с корзинами(69)
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> result = new ArrayList<>();
        Map<Integer, Good> goods = getAllGoods();
        try (Connection connection = getConnection();) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORAGE_LIST;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getLong(1), goods.get((int) rs.getLong(2)), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
                result.add(item);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot load items!", exception);
        }
    }

    public ArrayList<Item> getAllSoldItems() {
        ArrayList<Item> result = new ArrayList<>();
        Map<Integer, Good> goods = getAllGoods();
        try (Connection connection = getConnection();) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM SOLD_LIST;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getLong(1), goods.get((int) rs.getLong(2)), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
                result.add(item);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot load sold items!", exception);
        }
    }

    //Map<K, V> это удобно, так как мы работаем с ID, в листе нужно было бы перебирать
    public Map<Integer, Good> getAllGoods() {
        Map<Integer, Good> result = new HashMap<>();
        try (Connection connection = getConnection();) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CATALOG;");
            ResultSet rs = statement.executeQuery();

            String goodType;
            Good good = null;
            boolean isWare;

            while (rs.next()) {
                goodType = rs.getString(3);
                isWare = true;

                if (goodType.equals("Краска")) {
                    good = new Paint(rs.getLong(1), rs.getString(2), goodType,
                            BigDecimal.valueOf(rs.getDouble(4)), rs.getString(5),
                            rs.getDouble(6), rs.getBoolean(7), rs.getString(8), rs.getString(9));
                    isWare = false;
                }
                if (goodType.equals("Штукатурка")) {
                    good = new Plaster(rs.getLong(1), rs.getString(2), goodType,
                            BigDecimal.valueOf(rs.getDouble(4)), rs.getString(5),
                            rs.getDouble(6), rs.getBoolean(7), rs.getString(8), rs.getDouble(9));
                    isWare = false;
                }
                if (goodType.equals("Грунтовка")) {
                    good = new Priming(rs.getLong(1), rs.getString(2), goodType,
                            BigDecimal.valueOf(rs.getDouble(4)), rs.getString(5),
                            rs.getDouble(6), rs.getBoolean(7), rs.getString(8), rs.getDouble(9));
                    isWare = false;
                }
                if (isWare) {
                    good = new Ware(rs.getLong(1), rs.getString(2), goodType,
                            BigDecimal.valueOf(rs.getDouble(4)));
                }

                result.put(Math.toIntExact(good.getID()), good);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot load goods", exception);
        }
    }

}