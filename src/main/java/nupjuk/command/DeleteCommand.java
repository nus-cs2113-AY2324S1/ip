package nupjuk.command;
import nupjuk.*;
import java.io.IOException;
import static nupjuk.Printer.printLine;


/**
 * DeleteCommand class
 * get commands starts with "delete" and execute it
 * delete a task from task list
 */
public class DeleteCommand {
    public boolean execute(TaskList tasks, String[] tokens, Storage storage) throws IOException{
        int del_idx;
        try {
            del_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
        } catch (ArrayIndexOutOfBoundsException e){
            printLine("☹ OOPS!!! <delete> needs one integer parameter!");
            System.out.println("    ____________________________________________________________\n");
            return false;
        } catch (NumberFormatException e) {
            printLine("☹ OOPS!!! Task number should be one integer!");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        Task del_task;

        try {
            del_task = tasks.getTask(del_idx);
            tasks.removeTask(del_idx);
        } catch (IndexOutOfBoundsException e){
            printLine("☹ OOPS!!! Task number is out of List!");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        printLine("Noted. I've removed this task:");
        printLine(" [" + del_task.getStatusIcon() + "] " + del_task.getDescription());
        printLine(String.format("now you have %d tasks in the list", tasks.getSize()));
        System.out.println("    ____________________________________________________________\n");
        storage.saveTask(tasks);
        return false;
    }
}
