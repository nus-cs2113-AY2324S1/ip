import java.util.Scanner;

public class Fredbot {
    public static void addTasks(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Task(task);
        Task.setNumTask(numTask+1);
    }

    public static void changeStatus(Task[] tasks, boolean mark, int index) {
        tasks[index - 1].setDone(mark);
        if (mark) {
            String message = "    Nice! I've marked this task as done:\n";
            message += "    [X] " + tasks[index-1].getTaskDesc();
            printMessage(message);
        }
        else {
            String message = "    Nice! I've marked this task as not done yet:\n";
            message += "    [ ] " + tasks[index-1].getTaskDesc();
            printMessage(message);
        }
    }
    public static void printTasks(Task[] tasks) {
        StringBuilder tasklist = new StringBuilder();
        tasklist.append("    Here are the tasks in your list\n");
        int numTask = Task.getNumTask();
        for (int i = 0; i < numTask; i++) {
            tasklist.append("    ").append(i + 1).append(".[");
            if (tasks[i].isDone()) {
                tasklist.append("X");
            } else {
                tasklist.append(" ");
            }
            tasklist.append("] ").append(tasks[i].getTaskDesc()).append("\n");
        }
        printMessage(tasklist.toString());
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
        String greeting = "     Hello! I'm Fredbot\n" +
                "     What can I do for you?";
        String farewell = "     Bye. Hope to see you again soon!";
        printMessage(greeting);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printTasks(tasks);
            }
            else if (line.startsWith("mark")) {
                int index = Integer.parseInt(line.substring(5));
                changeStatus(tasks, true, index);
            }
            else if (line.startsWith("unmark")) {
                int index = Integer.parseInt(line.substring(7));
                changeStatus(tasks, false, index);
            }
            else {
                addTasks(tasks, line);
                printMessage("    added: " + line);
            }
            line = in.nextLine();
        }
        printMessage(farewell);
    }
}
