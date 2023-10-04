import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface object that handles the format and content of various outputs to the user
 * through command line interface.
 */
public class Ui {
    private static final String LINE_DIVIDER = "\t---------------------------------------";
    protected Scanner scanner;

    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    public void showStartMessage() {
        String intro = "~~~~~~~~~~~~~~~~~~~\n"
                        + "Hello! I'm Wenny!\n"
                        + "How may I help you?\n"
                        + "~~~~~~~~~~~~~~~~~~~";
        System.out.println(intro);
    }

    public void showByeMessage() {
        showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
    }
    public void showTasks(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("\tYou do not have any task in the list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        showLine();
    }

    public void showAddTask(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + tasks.get(tasks.size()-1));
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
        showLine();
    }

    public void showDeleteTask(TaskList taskList, Task removedTask) {
        ArrayList<Task> tasks = taskList.getTasks();
        showLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + removedTask);
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
        showLine();
    }

    public void showMark(TaskList taskList, String taskID, boolean isDone) {
        ArrayList<Task> tasks = taskList.getTasks();
        int index = Integer.parseInt(taskID);
        showLine();
        if (isDone) {
            System.out.println("\tNice! I've marked this task as done:");
        } else {
            System.out.println("\tNice! I've marked this task as undone:");
        }
        System.out.println("\t" + tasks.get(index-1));
        showLine();
    }

    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    public void showError(Exception e) {
        showLine();
        System.out.println("\t" + e);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showFind(ArrayList<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("\tKeyword does not match any task in the list.");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1 + ".");
                System.out.println(matchingTasks.get(i));
            }
        }
        showLine();
    }
}
