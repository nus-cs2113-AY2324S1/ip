import java.util.Scanner;

public class Spaceman {
    public static void main(String[] args) {
        String logo = "  ____  _____   ___    _____ _____ __    __   ___   __   __\n"
            + "/     /|  __ \\ /   \\  /   __|     |  \\  /  | /   \\ |  \\ |  |\n"
            + "\\   __\\| |__) |  _  \\|   /  |   __|   \\/   |/  _  \\|   \\|  |\n"
            + " \\__   |  ___/  |_|  |  |   |   __|        |  |_|  |       |\n"
            + "/      | |   |   _   |   \\__|     |   __   |   _   |       |\n"
            + "|____ /|_|   |__| |__|\\_____|_____|__|  |__|__| |__|__|\\___|\n";

        Task[] taskList = new Task[100];
        int count = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Spaceman");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                printList(taskList);
            } else if (text.startsWith("mark")) {
                String[] markDetails = text.split(" ");
                markTask(taskList, Integer.parseInt(markDetails[1]));
            } else if (text.startsWith("unmark")) {
                String[] unMarkDetails = text.split(" ");
                unMarkTask(taskList, Integer.parseInt(unMarkDetails[1]));
            } else {
                addToList(taskList, text);
                count++;
            }
            text = sc.nextLine();
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------");
    }

    public static void addToList(Task[] taskList, String taskName){
        Task task = new Task(taskName);
        taskList[Task.getTaskCount()-1] = task;
        System.out.println("------------------------------------------------------------");
        System.out.println("added: " + taskName);
        System.out.println("------------------------------------------------------------");
    }

    public static void printList(Task[] tasks){
        String mark;
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++){
            if (tasks[i].getTaskStatus()){
                mark = "X";
            } else {
                mark = " ";
            }
            System.out.println(i + 1 + ". [" + mark + "] " + tasks[i].getTaskDescription());
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void markTask(Task[] tasks, int taskIndex){
        tasks[taskIndex-1].markTask();
        System.out.println("------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + tasks[taskIndex-1].getTaskDescription());
        System.out.println("------------------------------------------------------------");
    }

    public static void unMarkTask(Task[] tasks, int taskIndex){
        tasks[taskIndex-1].unMarkTask();
        System.out.println("------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + tasks[taskIndex-1].getTaskDescription());
        System.out.println("------------------------------------------------------------");
    }

}
