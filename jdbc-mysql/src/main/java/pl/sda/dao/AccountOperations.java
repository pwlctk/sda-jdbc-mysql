package pl.sda.dao;

import java.sql.*;

public class AccountOperations {
    private Connection connection;

    public AccountOperations(Connection connection) {
        this.connection = connection;
    }

    public void addNewAccount(String number) {
        String query = "insert into Account(balance, number, creation_date) values(0, ? ,NOW())";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, number);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(String number) throws SQLException {
        String query = "UPDATE account SET close_date=NOW() WHERE number = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, number);
        statement.execute();
        statement.close();
    }

    public void setAccount(int id, int balance, String number, String creationDate, String closeDate) {
        String query = "UPDATE account SET balance = ?, number = ?, creation_date = STR_TO_DATE(?, \"%Y.%m.%d\"), close_date = STR_TO_DATE(?, \"%Y.%m.%d\") WHERE idAccount = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, balance);
            statement.setString(2, number);
            statement.setString(3, creationDate);
            statement.setString(4, closeDate);
            statement.setInt(5, id);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printAccountInfo(int id) {
        String query = "SELECT * FROM Account WHERE idAccount = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            executePreparedStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printAllAccountsInfo() {
        String query = "SELECT * FROM Account";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                printResult(resultSet);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAccount(int id) {
        String query = "DELETE FROM Account WHERE idAccount = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    private void printResult(ResultSet resultSet) throws SQLException {
        System.out.println("Dane konta o ID: " + resultSet.getInt("idAccount"));
        System.out.println("Saldo: " + resultSet.getInt("balance"));
        System.out.println("Numer konta: " + resultSet.getString("number"));
        System.out.println("Data utworzenia: " + resultSet.getDate("creation_date"));
        System.out.println("Data zamknięcia: " + resultSet.getDate("close_date"));
        System.out.println("================================================");
    }

}
