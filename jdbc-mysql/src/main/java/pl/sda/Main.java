package pl.sda;

import pl.sda.bo.CommandRunner;
import pl.sda.dao.AccountJdbcDAO;
import pl.sda.dao.AccountOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccountJdbcDAO dao = new AccountJdbcDAO();
        Connection connection = dao.openConnection();
        AccountOperations accountOperations = new AccountOperations(connection);

        CommandRunner commandRunner = new CommandRunner(accountOperations);
        Scanner input = new Scanner(System.in);
        String command;

        do {
            System.out.println("Podaj komendę: ");
            command = input.nextLine();
            commandRunner.runCommand(command);

        } while (!command.equalsIgnoreCase("exit"));

        dao.closeConnection(connection);
    }


}
