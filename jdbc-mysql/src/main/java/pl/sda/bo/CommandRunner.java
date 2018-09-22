package pl.sda.bo;

import pl.sda.bo.command.*;
import pl.sda.dao.AccountOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRunner  {
    private final Map<String, Command> map = new HashMap<>();

    public CommandRunner(AccountOperations accountOperations) {
        addCommand(new Help(map));
        addCommand(new Exit());
        addCommand(new AddAccount(accountOperations));
        addCommand(new AccountInfo(accountOperations));
        addCommand(new EditAccount(accountOperations));
        addCommand(new PrintAllAccounts(accountOperations));
        addCommand(new DeleteAccount(accountOperations));

    }

    private void addCommand(Command command) {
        String key = command.getCommandName().trim().toUpperCase();
        map.put(key, command);
    }

    private Optional<Command> getCommand(String actionName) {
        return Optional.ofNullable(map.get(actionName.trim().toUpperCase()));
    }

    public void runCommand(String actionName) {
        Optional<Command> command = getCommand(actionName);
        if (command.isPresent()) {
            command.get().run();
        } else {
            System.out.println("Niepoprawna komenda! Wpisz help aby wyświetlić listę komend");
        }
    }
}
