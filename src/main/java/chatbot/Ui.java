package chatbot;

import java.util.ArrayList;

public class Ui {
    /**
     * Show greeting message when app first launches.
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void showGreetingMessage() {
        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);
    }
    /**
     * Show bye message when app is closing
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void showByeMessage() {
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(byeMsg);
    }
    /**
     * Show an error message, with additional formatting possible
     *
     * @param   message     the message to be displayed
     * @param   addLines    whether to wrap the message between to horizontal dashed-lines
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void showError(String message, boolean addLines) {
        if( addLines ) {
            System.out.println("____________________________________________________________");
        }
        System.out.println("Unknown exception. Error message: "  + message);
        if( addLines ) {
            System.out.println("____________________________________________________________");
        }
    }
    /**
     * Print the list of tasks
     *
     * @param   tasks       the list of tasks to be printed
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            //System.out.println(" " + (i + 1) + ".[" + tasks[i].getTypeIcon() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
    /**
     * Print the result of the `mark` command
     *
     * @param   tasks       the current list of tasks
     * @param   markTaskNo  the task number that was marked by the user
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printMarkResult(ArrayList<Task> tasks, int markTaskNo) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   [" + tasks.get(markTaskNo - 1).getStatusIcon() + "] " + tasks.get(markTaskNo - 1).getDescription());
        System.out.println("____________________________________________________________");
    }
    /**
     * Print the result of the `unmark` command
     *
     * @param   tasks           the current list of tasks
     * @param   unmarkedTaskNo  the task number that was unmarked by the user
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printUnmarkResult(ArrayList<Task> tasks, int unmarkedTaskNo) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   [" + tasks.get(unmarkedTaskNo - 1).getStatusIcon() + "] " + tasks.get(unmarkedTaskNo - 1).getDescription());
        System.out.println("____________________________________________________________");
    }
    /**
     * Print the result of the `todo` command
     *
     * @param   tasks       the current list of tasks
     * @param   task        the task that was added
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printTodoResult(ArrayList<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * Print the result of the `deadline` command
     *
     * @param   tasks       the current list of tasks
     * @param   task        the task that was added
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printDeadlineResult(ArrayList<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * Print the result of the `event` command
     *
     * @param   tasks       the current list of tasks
     * @param   task        the task that was added
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printEventResult(ArrayList<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * Print the result of the `delete` command
     *
     * @param   tasks       the current list of tasks
     * @param   task        the task that was added
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void printDeleteResult(ArrayList<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("   " + task);
        System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
