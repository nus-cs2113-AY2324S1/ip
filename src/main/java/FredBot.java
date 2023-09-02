import java.util.Scanner;

public class FredBot {
    public static void addTasks(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Task(task);
        Task.setNumTask(numTask+1);
    }

    public static void changeStatus(Task[] tasks, boolean mark, int index) {
        tasks[index - 1].setDone(mark);
        String message;
        if (mark) {
            message = "    Nice! I've marked this task as done:\n";
            message += "    [X] " + tasks[index-1].getTaskDesc();
        } else {
            message = "    Nice! I've marked this task as not done yet:\n";
            message += "    [ ] " + tasks[index-1].getTaskDesc();
        }
        printMessage(message);
    }
    public static void printTasks(Task[] tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append("    Here are the tasks in your list\n");
        int numTask = Task.getNumTask();
        for (int i = 0; i < numTask; i++) {
            String number = (i + 1) + ".";
            taskList.append("    ").append(number).append(tasks[i].toString()); // Can be formatted
        }
        printMessage(taskList.toString());
    }
    public static void printMessage(String message) {
        System.out.print("    ____________________________________________________________\n");
        System.out.println(message);
        System.out.println("    ____________________________________________________________\n");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Task[] tasks = new Task[100];
        String GREETING = "     Hello! I'm Fredbot\n" +
                "     What can I do for you?";
        String FAREWELL = "     Bye. Hope to see you again soon!";
        printMessage(GREETING);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printTasks(tasks);
            } else if (line.startsWith("mark")) {
                int index = Integer.parseInt(line.substring(5));
                changeStatus(tasks, true, index);
            } else if (line.startsWith("unmark")) {
                int index = Integer.parseInt(line.substring(7));
                changeStatus(tasks, false, index);
            } else {
                addTasks(tasks, line);
                printMessage("    added: " + line);
            }
            line = in.nextLine();
        }
        printMessage(FAREWELL);
    }
}
