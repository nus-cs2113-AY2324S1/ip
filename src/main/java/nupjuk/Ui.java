package nupjuk;

import nupjuk.command.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static nupjuk.Printer.printLine;


/**
 * Shows the User Interface
 * Print things in specific format
 */

public class Ui {
    public Ui(){
        System.out.println("    ____________________________________________________________");
        printLine("Hello! I'm nupjuk.Nupjuk");
        printLine("What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }




    /**
     * run the user-input commmand
     * If user inputs "bye", returns true to terminate infinite loop of run() function
     * Otherwise, returns false
     *
     * @param cmd nupjuk.command string that user inputted
     * @param tasks List of Todos/Deadlines/Events
     * @param storage Where to save and load
     * @return terminates or not
     * @throws IOException if problem in input and parsing
     */
    public boolean runCommand(String cmd, TaskList tasks, Storage storage) throws IOException {
        String[] tokens = cmd.split(" ", 2);
        System.out.println("    ____________________________________________________________");
        if(cmd.equals("bye")){ // bye nupjuk.command
            return true;
        } else if(cmd.equals("list")){
            ListCommand command = new ListCommand();
            return command.execute(tasks);
        } else if(tokens[0].equals("mark")){
            MarkCommand command = new MarkCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("unmark")){
            UnmarkCommand command = new UnmarkCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("todo")) {
            TodoCommand command = new TodoCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("deadline")){
            DeadlineCommand command = new DeadlineCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("event")){
            EventCommand command = new EventCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("delete")){
            DeleteCommand command = new DeleteCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("find")){
            FindCommand command = new FindCommand();
            return command.execute(tasks, tokens);
        } else{
            // nupjuk.command not matched
            printLine("☹ Sorry, I cannot understand your nupjuk.command: " + cmd);
            System.out.println("    ____________________________________________________________\n");
        }
        return false;
    }

    /**
     * Prints a termination message.
     */
    public void exitProg(){
        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
