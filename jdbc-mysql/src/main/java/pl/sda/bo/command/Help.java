package pl.sda.bo.command;

import java.util.Map;

public class Help implements Command {

    private Map<String, Command> commands;

    public Help(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void run() {
        for (Command value : commands.values()) {
            System.out.println(value.getHelpLine());
        }
    }

    @Override
    public String getHelpMessage() {
        return "Wyświetla listę dostępnych komend";
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
