package pl.sda.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccountJdbcDAO jdbc = new AccountJdbcDAO();
        Connection connection = jdbc.openConnection();
        System.out.println("Baza: " + connection.getCatalog());


        //jdbc.findAll(connection);
        //jdbc.findById(connection, 5);
        //jdbc.findByBallance(connection, 2500);
        //jdbc.findByNumber(connection, "101010320000323233300000252123");
        //jdbc.findByCreationDate(connection,"2018.09.16");
        //jdbc.findByAfterCreationDate(connection,"2019.09.16");
        System.out.println("Liczba wierszy w tabeli Account: " + jdbc.accountCount(connection));
        jdbc.closeConnection(connection);
    }


}
