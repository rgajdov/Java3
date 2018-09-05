import java.sql.*;

class Database {
    private static Connection connection = null;
    private final String DRIVER_NAME = "org.sqlite.JDBC";
    private final String DB_NAME = "jdbc:sqlite:src/main/resources/students.db";
    private final String TABLE_NAME = "students";

    Database() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    boolean insert(String surname, int point) {
        int studentId = 0;
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (surname, point) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, surname);
            ps.setInt(2, point);
            ps.executeUpdate();
            ResultSet getKeys = ps.getGeneratedKeys();
            if(getKeys.next()) {
                studentId = getKeys.getInt(1);
            }
            System.out.println("Добавлена запись. Id: " + studentId + ", фамилия: " + surname + ", балл: " + point);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean update(int id, String surname, int point) {
        int result;
        try (PreparedStatement ps = getConnection().prepareStatement(
                "UPDATE " + TABLE_NAME + " set surname=?, point=? where student_id=?;")) {
            ps.setString(1, surname);
            ps.setInt(2, point);
            ps.setInt(3, id);
            result = ps.executeUpdate();
            if (result != 0) {
                System.out.println("Обновлена запись. Id: " + id + ", фамилия: " + surname + ", балл: " + point);
                return true;
            } else {
                System.out.println("Ошибка обновления записи (Id: " + id + " не существует!)");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean delete(int id) {
        int result;
        try (PreparedStatement ps = getConnection().prepareStatement("DELETE FROM " + TABLE_NAME + " where student_id=?;")) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
            if (result != 0) {
                System.out.println("Удалена запись. Id: " + id);
                return true;
            } else {
                System.out.println("Записи с Id: " + id + " не существует!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    Connection getConnection() {
        if (connection == null)
            try {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.getConnection(DB_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return connection;
    }

    void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
