import neo.task.Task;
import java.util.ArrayList;

public class Neo {

    private static void handleInput(String line, ArrayList<Task> list) {
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                TaskList.printList();
            } else if (line.startsWith("mark")) {
                TaskList.markTask(line);
            } else if (line.startsWith("unmark")) {
                TaskList.unmarkTask(line);
            } else if (line.startsWith("delete")) {
                TaskList.deleteTask(line);
            } else if (line.startsWith("event")) {
                TaskList.handleEvent(line);
            } else if (line.startsWith("deadline")) {
                TaskList.handleDeadline(line);
            } else if (line.startsWith("todo")){
                TaskList.handleTodo(line);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Storage.updateFile(list);
            line = Ui.readInput();
        }
    }

    public static void main(String[] args) {
        Storage.findFile(TaskList.getList());
        Ui.welcomeMessage();
        String input = Ui.readInput();
        handleInput(input, TaskList.getList());
        Ui.byeMessage();
    }
}
