package pl.sda.bo.command;

import pl.sda.dao.AccountOperations;

import java.util.Scanner;

public class AddAccount implements Command {
    private AccountOperations accountOperations;

    public AddAccount(AccountOperations accountOperations) {
        this.accountOperations = accountOperations;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer konta: ");
        String number = scanner.nextLine();
        accountOperations.addNewAccount(number);
    }

    @Override
    public String getHelpMessage() {
        return "Dodaje nowe konto do bazy danych";
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}
