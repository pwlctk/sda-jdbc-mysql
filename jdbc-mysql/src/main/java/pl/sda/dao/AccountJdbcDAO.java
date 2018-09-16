package pl.sda.dao;

import java.sql.*;
import java.util.Properties;

class AccountJdbcDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/zad1";
    private static final String DB_USER = "scott";
    private static final String DB_PASSWORD = "scott";

    Connection openConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", DB_USER);
        properties.setProperty("password", DB_PASSWORD);
        properties.setProperty("useSSL", "false");
        properties.setProperty("serverTimezone", "UTC");

        return DriverManager.getConnection(DB_URL, properties);
    }

    void closeConnection(Connection connection) throws SQLException {
        System.out.println("Baza: " + connection.getCatalog() + " zamknięta!");
        connection.close();
    }

    private void createStatement(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            printResult(resultSet);
        }
        resultSet.close();
        statement.close();
    }

    private void createPrepareStatementById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM Account WHERE idAccount = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        executePreparedStatement(statement);
    }

    private void createPrepareStatementByBallance(Connection connection, int ballance) throws SQLException {
        String query = "SELECT * FROM Account WHERE ballance = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ballance);
        executePreparedStatement(statement);
    }

    private void createPrepareStatementByNumber(Connection connection, String number) throws SQLException {
        String query = "SELECT * FROM Account WHERE number = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, number);
        executePreparedStatement(statement);
    }

    private void createPrepareStatementByCreationDate(Connection connection, String creationDate) throws SQLException {
        String query = "SELECT * FROM Account WHERE creation_date = STR_TO_DATE(?, \"%Y.%m.%d\")";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, creationDate);
        executePreparedStatement(statement);
    }

    private void createPrepareStatementByAfterCreationDate(Connection connection, String creationDate) throws SQLException {
        String query = "SELECT * FROM Account WHERE creation_date > STR_TO_DATE(?, \"%Y.%m.%d\")";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, creationDate);
        executePreparedStatement(statement);
    }

    private void executePreparedStatement(PreparedStatement statement) throws SQLException {
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            printResult(resultSet);
        }
        resultSet.close();
        statement.close();
    }

    void findAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Account";
        createStatement(connection, query);
    }

    void findById(Connection connection, int id) throws SQLException {
        createPrepareStatementById(connection, id);
    }

    void findByBallance(Connection connection, int ballance) throws SQLException {
        createPrepareStatementByBallance(connection, ballance);
    }

    void findByNumber(Connection connection, String number) throws SQLException {
        createPrepareStatementByNumber(connection, number);
    }

    void findByCreationDate(Connection connection, String creationDate) throws SQLException {
        createPrepareStatementByCreationDate(connection, creationDate);
    }

    void findByAfterCreationDate(Connection connection, String creationDate) throws SQLException {
        createPrepareStatementByAfterCreationDate(connection, creationDate);
    }

    int accountCount(Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM Account";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt("total");
        }
        resultSet.close();
        statement.close();

        return count;

    }

    private void printResult(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getInt("idAccount") + " ");
        System.out.print(resultSet.getInt("balance") + " ");
        System.out.print(resultSet.getString("number") + " ");
        System.out.print(resultSet.getDate("creation_date") + " ");
        System.out.println(resultSet.getDate("close_date"));
    }
}
