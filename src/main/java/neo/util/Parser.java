package neo.util;

import neo.task.Task;
import java.util.ArrayList;

public abstract class Parser {
    public static void handleInput(String input, ArrayList<Task> list) {
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                TaskList.printList();
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
