package pl.sda.command;

import pl.sda.dao.AccountOperations;

import java.util.Scanner;

public class EditAccount implements Command {
    private AccountOperations accountOperations;

    public EditAccount(AccountOperations accountOperations) {
        this.accountOperations = accountOperations;
    }

    @Override
    public void run() {
        accountOperations.printAllAccountsInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id konta: ");
        int id = scanner.nextInt();
        System.out.println("Podaj saldo konta: ");
        int balance = scanner.nextInt();
        System.out.println("Podaj numer konta: ");
        String number = scanner.nextLine();
        System.out.println("Podaj datę utworzenia konta(RRRR.MM.DD): ");
        String creationDate = scanner.nextLine();
        System.out.println("Podaj datę zamknięcia konta(RRRR.MM.DD): ");
        String closeDate = scanner.nextLine();
        accountOperations.setAccount(id, balance, number, creationDate, closeDate);
    }

    @Override
    public String getHelpMessage() {
        return "Edycja konta";
    }

    @Override
    public String getCommandName() {
        return "edit";
    }
}
