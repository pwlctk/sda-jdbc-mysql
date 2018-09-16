package pl.sda.command;

public class Exit implements Command {
    @Override
    public void run() {
        System.out.println("Koniec programu!");
        System.exit(0);
    }

    @Override
    public String getHelpMessage() {
        return "Wyjście z programu";
    }

    @Override
    public String getCommandName() {
        return "exit";
    }
}
