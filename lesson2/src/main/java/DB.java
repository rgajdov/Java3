import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB {

    private static Connection connection = null;
    private final String DRIVER_NAME = "org.sqlite.JDBC";
    private final String DB_NAME = "jdbc:sqlite:src/main/resources/sqlite.db";
    private final String TABLE_NAME = "products";

    public DB() throws SQLException {
        getConnection();

        System.out.println("1. Создать таблицу и заполнить ее данными (команда /createtable)");
        System.out.println("2. Запрос цены товара (команда /price имя_товара)");
        System.out.println("3. Изменение цены товара (команда /changeprice имя_товара новая_цена)");
        System.out.println("4. Вывод товаров в ценовом диапазоне (команда /goodsforprice граница1 граница2)");
        System.out.println("5. Выход из программы (команда /exit)");

        String inputString;
        boolean quitFlag = false;

        while (!quitFlag) {
            inputString = new Scanner(System.in).nextLine();
            if (inputString.equals("/exit")) quitFlag = true;

            String[] params;
            params = inputString.split(" ");

            switch (params[0]) {
                case "/createtable": {
                    createTable();
                    break;
                }
                case "/price": {
                    if (getCost(params[1]) == 0) {
                        System.out.println(params[1] + ": Такого товара нет!");
                    } else {
                        System.out.println(getCost(params[1]));
                    }
                    break;
                }
                case "/changeprice": {
                    updatePrice(params[1], Integer.parseInt(params[2]));
                    break;
                }
                case "/goodsforprice": {
                    System.out.println(getProductsBetween(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                    break;
                }
                default:
            }

        }

        closeConnection();
    }

    private void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "PRAGMA encoding=\"utf-8\"" + ";" +
                    "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                            "CREATE TABLE " + TABLE_NAME +
                            "(id INTEGER(6) PRIMARY KEY NOT NULL, name CHAR(255) NOT NULL, cost NUMERIC(6) NOT NULL);"
            );
            connection.setAutoCommit(false);
            for (int i = 1; i <= 10000 ; i++) {
                add(i, "product" + i, i * 10);
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "DELETE FROM " + TABLE_NAME + ";"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(int id, String title, int cost) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (id, name, cost) VALUES ('" + id + "', '" + title + "', '" + cost + "');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + TABLE_NAME +
                    " where id='" + id + "';"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePrice(String title, int cost) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE " + TABLE_NAME + " set cost=? where name=?;")) {
            ps.setInt(1, cost);
            ps.setString(2, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getCost(String title) {
        ResultSet rs;
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT cost FROM " + TABLE_NAME + " WHERE name=?;")) {
            ps.setString(1, title);
            rs = ps.executeQuery();
            return rs.getInt("cost");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return 0;
    }

    private List<String> getProductsBetween(int lowLevel, int highLevel) {
        ResultSet rs;
        List<String> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
             "SELECT name FROM " + TABLE_NAME + " WHERE cost BETWEEN ? AND ?;")) {
            ps.setInt(1, lowLevel);
            ps.setInt(2, highLevel);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Connection getConnection() {
        if (connection == null)
            try {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.getConnection(DB_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return connection;
    }

    private void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
