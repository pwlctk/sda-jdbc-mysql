package pl.sda.command;

public interface Command {
    void run();

    String getHelpMessage();

    String getCommandName();

    default String getHelpLine() {
        return getCommandName() + " - " + getHelpMessage();
    }
}
