package pl.sda.dao;

import java.sql.*;
import java.util.Properties;

public class AccountJdbcDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/zad1";
    private static final String DB_USER = "scott";
    private static final String DB_PASSWORD = "scott";

    public Connection openConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", DB_USER);
        properties.setProperty("password", DB_PASSWORD);
        properties.setProperty("useSSL", "false");
        properties.setProperty("serverTimezone", "UTC");

        return DriverManager.getConnection(DB_URL, properties);
    }

    public void closeConnection(Connection connection) throws SQLException {
        //System.out.println("Baza: " + connection.getCatalog() + " zamknięta!");
        connection.close();
    }


}
