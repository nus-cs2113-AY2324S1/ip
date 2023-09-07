import java.util.Scanner;
import java.util.ArrayList;

public class Nuke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }
    }

    public static void printGreetingMessage() {
        printLine();
        System.out.println("     Hello! I'm Nuke\n" + "     What can I do for you?");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void dialogue(ArrayList<Task> taskList) {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        if (item.equals("bye")) {
            printExitMessage();
            return;
        } else if (item.equals("list")) {
            list(taskList);
        } else if (item.length() > 4 && item.substring(0, 4).equals("mark")) {
            String[] splitItem = item.split(" ");
            int listIndex = Integer.parseInt(splitItem[1]);
            mark(taskList, listIndex);
        } else if (item.length() > 6 && item.substring(0, 6).equals("unmark")) {
            String[] splitItem = item.split(" ");
            int listIndex = Integer.parseInt(splitItem[1]);
            unmark(taskList, listIndex);
        } else {
            add(taskList, item);
        }
        dialogue(taskList);
    }

    public static void add(ArrayList<Task> taskList, String item) {
            printLine();
            Task newTask = new Task(item);
            taskList.add(newTask);
            System.out.printf("     added: %s\n", item);
            printLine();
    }

    public static void list(ArrayList<Task> taskList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("     %d.%s %s\n", i+1, taskList.get(i).getStatusIcon(), taskList.get(i).description);
        }
        printLine();
    }

    public static void mark(ArrayList<Task> taskList, int listIndex) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        taskList.get(listIndex - 1).mark();;
        System.out.printf("       %s %s\n", taskList.get(listIndex - 1).getStatusIcon(), taskList.get(listIndex - 1).description);
        printLine();
    }

    public static void unmark(ArrayList<Task> taskList, int listIndex) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        taskList.get(listIndex - 1).unmark();
        System.out.printf("       %s %s\n", taskList.get(listIndex - 1).getStatusIcon(), taskList.get(listIndex - 1).description);
        printLine();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        ArrayList<Task> taskList = new ArrayList<Task>();
        dialogue(taskList);
    }
}
