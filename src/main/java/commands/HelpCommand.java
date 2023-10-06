package commands;

import ascii.AsciiArt;
import main.Parser;

/**
 * Represents a help Command to get all the commands for this program
 */
public class HelpCommand extends Command {

    @Override
    public void execute(String statement, Parser processor) throws IllegalArgumentException {
        if (!statement.isEmpty()){
            throw new IllegalArgumentException ("uwu dont write anything after 'help' " + AsciiArt.getArt("cry"));
        }
        System.out.println("| Commands                             | Description                                                          |\n" +
                "|--------------------------------------|----------------------------------------------------------------------|\n" +
                "| help                                 | Display all the commands for the program                             |\n" +
                "| todo <name>                          | Add a todo task                                                      |\n" +
                "| event <name> /from <start> /to <end> | Add an event task with `<start>` time and `<end>` time               |\n" +
                "| deadline <name> /by <end>            | Add a deadline task with `<end>` time                                |\n" +
                "| list                                 | Print out the whole task list                                        |\n" +
                "| mark <index>                         | Mark the task at `<index>` as complete                               |\n" +
                "| unmark <index>                       | Mark the task at `<index>` as incomplete                             |\n" +
                "| find <string>                        | Find a `<string>` in the task list and print out all related task(s) |\n" +
                "| delete <index>                       | Delete the task at `<index>`                                         |\n" +
                "| bye                                  | Exit the program                                                     |\n" +
                "| ascii <boolean>                      | Enable/Disable ascii art                                             |");
    }
}
