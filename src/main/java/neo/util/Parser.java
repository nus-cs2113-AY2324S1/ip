package neo.util;

import neo.task.Task;
import neo.task.TaskList;
import java.util.ArrayList;

/**
 * Deals with making sense of the user command. This class is abstract as
 * its main purpose is to provide methods relating to making sense of the user command.
 */
public abstract class Parser {

    /**
     * Calls relevant methods depending on the input from user. Write any changes in list
     * to data.txt file.
     *
     * @param input This is the input from user in CLI.
     * @param list This is the list containing all tasks.
     */
    public static void handleInput(String input, ArrayList<Task> list) {
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                TaskList.printList();
            } else if (input.startsWith("find")) {
                TaskList.find(input);
            } else if (input.startsWith("mark")) {
                TaskList.markTask(input);
            } else if (input.startsWith("unmark")) {
                TaskList.unmarkTask(input);
            } else if (input.startsWith("delete")) {
                TaskList.deleteTask(input);
            } else if (input.startsWith("event")) {
                TaskList.handleEvent(input);
            } else if (input.startsWith("deadline")) {
                TaskList.handleDeadline(input);
            } else if (input.startsWith("todo")){
                TaskList.handleTodo(input);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Storage.updateFile(list);
            input = Ui.readInput();
        }
    }
}
