package zenbot;

import commands.Command;
import commands.CommandParser;
import commands.Farewell;
import exceptions.OutOfRangeException;
import exceptions.TaskEmptyDescriptionException;
import exceptions.UnknownCommandException;
import tasks.Tasklist;

public class ZenBot { 
    public static void main(String[] args) throws UnknownCommandException, TaskEmptyDescriptionException {
        UI.printWelcomeScreen();

        Input input = new Input();
        Tasklist tasks = new Tasklist();
        CommandParser commandParser = new CommandParser();
        Command nextCommand = null;

        while (!(nextCommand instanceof Farewell)) {
            try {
                nextCommand = commandParser.parse(input.getInput(), tasks);
                nextCommand.execute();
            } catch (UnknownCommandException e) {
                System.out.println("\tUnknown command, please try again");
                continue;
            } catch (TaskEmptyDescriptionException e) {
                System.out.println("\tTask description cannot be empty, please try again");
                continue;
            } catch (OutOfRangeException e) {
                System.out.println("\tTask number is out of range, please try again");
                continue;
            }
        }
    }
}
