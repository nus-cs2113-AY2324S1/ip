package zenbot;

import commands.Command;
import commands.CommandParser;
import commands.Farewell;
import java.io.IOException;
import exceptions.OutOfRangeException;
import exceptions.TaskEmptyDescriptionException;
import exceptions.UnknownCommandException;
import tasks.Tasklist;

/**
 * Represents the main class of the ZenBot application
 */
public class ZenBot { 
    public static void main(String[] args) throws UnknownCommandException, TaskEmptyDescriptionException, IOException {
        UI.printWelcomeScreen();

        Tasklist tasks = new Tasklist();
        Command nextCommand = null;
        FileHandler.readFromFile(tasks);

        while (!(nextCommand instanceof Farewell)) {
            try {
                nextCommand = CommandParser.parse(Input.getInput(), tasks);
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
