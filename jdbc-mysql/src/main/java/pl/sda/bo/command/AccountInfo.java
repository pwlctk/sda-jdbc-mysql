package pl.sda.bo.command;

import pl.sda.dao.AccountOperations;

import java.util.Scanner;

public class AccountInfo implements Command {
    private AccountOperations accountOperations;

    public AccountInfo(AccountOperations accountOperations) {
        this.accountOperations = accountOperations;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id konta: ");
        int id = scanner.nextInt();
        accountOperations.printAccountInfo(id);
    }

    @Override
    public String getHelpMessage() {
        return "Wyświetla informacje o koncie";
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}
