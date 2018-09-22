package pl.sda.bo.command;

import pl.sda.dao.AccountOperations;

public class PrintAllAccounts implements Command {
    private AccountOperations accountOperations;

    public PrintAllAccounts(AccountOperations accountOperations) {
        this.accountOperations = accountOperations;
    }
    @Override
    public void run() {
        accountOperations.printAllAccountsInfo();
    }

    @Override
    public String getHelpMessage() {
        return "Wyświetla dane wszystkich kont";
    }

    @Override
    public String getCommandName() {
        return "all";
    }
}
