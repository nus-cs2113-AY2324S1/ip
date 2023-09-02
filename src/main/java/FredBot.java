import java.util.Scanner;

public class FredBot {
    public static void addTask(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Task(task);
        printAddTask("      " + tasks[numTask].toString() + "\n");
        Task.setNumTask(numTask+1);
    }

    public static void addTodo(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Todo(task);
        printAddTask("      " + tasks[numTask].toString() + "\n");
        Task.setNumTask(numTask+1);
    }

    public static void addDeadline(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Deadline(task.substring(0, task.indexOf(" /by")),
                task.substring(task.indexOf(" /by") + 5));
        printAddTask("      " + tasks[numTask].toString() + "\n");
        Task.setNumTask(numTask+1);
    }

    public static void addEvent(Task[] tasks, String task) {
        int numTask = Task.getNumTask();
        tasks[numTask] = new Event(task.substring(0, task.indexOf(" /from")),
                task.substring(task.indexOf(" /from") + 7, task.indexOf(" /to")),
                task.substring(task.indexOf(" /to") + 5));
        printAddTask("      " + tasks[numTask].toString() + "\n");
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
            taskList.append("    ").append(number).append(tasks[i].toString()).append("\n"); // Can be formatted
        }
        printMessage(taskList.toString());
    }

    public static void printAddTask(String message) {
        printMessage("    Got it. I've added this task:\n" + message + "    " +
                "Now you have " + (Task.getNumTask() +1) + " tasks in the list\n");
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
                int index = Integer.parseInt(line.substring(5).trim());
                changeStatus(tasks, true, index);
            } else if (line.startsWith("unmark")) {
                int index = Integer.parseInt(line.substring(7).trim());
                changeStatus(tasks, false, index);
            } else if (line.startsWith("todo")){
                addTodo(tasks, line.substring(5));
            } else if (line.startsWith("deadline")) {
                addDeadline(tasks, line.substring(9));
            } else if (line.startsWith("event")) {
                addEvent(tasks, line.substring(6));
            } else {
                addTask(tasks, line);
            }
            line = in.nextLine();
        }
        printMessage(FAREWELL);
    }
}
