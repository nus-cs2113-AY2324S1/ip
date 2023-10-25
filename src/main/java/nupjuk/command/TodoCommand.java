package nupjuk.command;
import nupjuk.*;
import java.io.IOException;
import static nupjuk.Printer.printLine;

/**
 * TodoCommand class
 * get commands starts with "todo" and execute it
 */
public class TodoCommand {
    public boolean execute(TaskList tasks, String[] tokens, Storage storage) throws IOException {
        try{
            FormatChecker.checkInputFormat(tokens);
        } catch (InputFormatException e){
            printLine("☹ OOPS!!! <todo> should be with task description");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        // make and add to list
        printLine("Got it. I've added this task:");
        Todo todo = new Todo(tokens[1]);
        tasks.addTask(todo);

        printLine(String.format("  [%s][%s] %s",
                todo.getTypeIcon(), todo.getStatusIcon(), todo.getDescription()));
        printLine(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        System.out.println("    ____________________________________________________________\n");
        storage.saveTask(tasks);
        return false;
    }
}
