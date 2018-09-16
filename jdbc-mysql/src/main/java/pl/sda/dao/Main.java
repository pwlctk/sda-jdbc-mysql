package pl.sda.dao;

import pl.sda.command.CommandRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccountJdbcDAO dao = new AccountJdbcDAO();
        Connection connection = dao.openConnection();
        //System.out.println("Baza: " + connection.getCatalog());


        //dao.findAll(connection);
        //dao.findById(connection, 8);
        //dao.findByBallance(connection, 2500);
        //dao.findByNumber(connection, "101010320000323233300000252123");
        //dao.findByCreationDate(connection,"2018.09.16");
        //dao.findByAfterCreationDate(connection,"2019.09.16");
        //System.out.println("Liczba wierszy w tabeli AccountOperations: " + dao.accountCount(connection));


        AccountOperations accountOperations = new AccountOperations(connection);
        //accountOperations.addNewAccount("27 1140 2006 0000 5002 0120 3423");
        //accountOperations.closeAccount("27 1140 2006 0000 5002 0120 3423");
        //accountOperations.setAccount(8,1000000,"50 0002 0123 1234 5678 9015 4","1985.02.20", null);
        //accountOperations.printAccountInfo(8);
        //accountOperations.deleteAccount(5);
        //accountOperations.printAllAccountsInfo();


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
