package nupjuk.command;
import nupjuk.Storage;
import nupjuk.TaskList;
import java.io.IOException;
import static nupjuk.Printer.printLine;

/**
 * UnmarkCommand class
 * get commands starts with "mark" and execute it
 * erasing mark on uncompleted tasks
 */
public class UnmarkCommand {
    public boolean execute(TaskList tasks, String[] tokens, Storage storage) throws IOException {
        int mark_idx;

        try {
            mark_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
        } catch (ArrayIndexOutOfBoundsException e){
            printLine("☹ OOPS!!! <unmark> needs one integer parameter!");
            System.out.println("    ____________________________________________________________\n");
            return false;
        } catch (NumberFormatException e) {
            printLine("☹ OOPS!!! Task number should be one integer!");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        try {
            tasks.getTask(mark_idx).unMark();
        } catch (IndexOutOfBoundsException e){
            printLine("☹ OOPS!!! Task number is out of List!");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        printLine("OK, I've marked this task as not done yet:");
        printLine(" [" + tasks.getTask(mark_idx).getStatusIcon() + "] " + tasks.getTask(mark_idx).getDescription());
        System.out.println("    ____________________________________________________________\n");
        storage.saveTask(tasks);
        return false;
    }
}
