package pl.sda.command;

import pl.sda.dao.AccountOperations;

import java.util.Scanner;

public class DeleteAccount implements Command {

    private AccountOperations accountOperations;

    public DeleteAccount(AccountOperations accountOperations) {
        this.accountOperations = accountOperations;
    }
    @Override
    public void run() {
        accountOperations.printAllAccountsInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id konta: ");
        int id = scanner.nextInt();
        accountOperations.deleteAccount(id);
    }

    @Override
    public String getHelpMessage() {
        return "Usuwa konto z bazy";
    }

    @Override
    public String getCommandName() {
        return "delete";
    }
}
