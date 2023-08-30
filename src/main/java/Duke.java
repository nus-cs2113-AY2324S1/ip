import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String CHATBOTNAME="Andrew Tate";
    static ArrayList<Task> TASKS = new ArrayList<Task>();

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + CHATBOTNAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        label:
        while (true){
            String commandGiven = myScanner.nextLine();
            String[] splitCommand = commandGiven.split("\\s+");
            String actionCommand = splitCommand[0];

            switch (actionCommand) {
                case "bye":
                    break label;
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < TASKS.size(); i++) {
                        Task task = TASKS.get(i);
                        System.out.println(i + 1 + ".[" + task.getStatusIcon() + "] " + task.description);
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    try{
                        int taskIndex = Integer.parseInt(splitCommand[1]);
                        Task taskToMark = TASKS.get(taskIndex-1);
                        taskToMark.markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Marked this task as done:");
                        System.out.println("[" + taskToMark.getStatusIcon() + "] " + taskToMark.description);
                        System.out.println("____________________________________________________________");

                    }catch(Exception e){
                        System.out.println("____________________________________________________________");
                        System.out.println("Invalid index, or other error!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "unmark":
                    try{
                        int taskIndex = Integer.parseInt(splitCommand[1]);
                        Task taskToUnmark = TASKS.get(taskIndex-1);
                        taskToUnmark.markAsUndone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Marked this task as undone:");
                        System.out.println("[" + taskToUnmark.getStatusIcon() + "] " + taskToUnmark.description);
                        System.out.println("____________________________________________________________");

                    }catch(Exception e){
                        System.out.println("____________________________________________________________");
                        System.out.println("Invalid index, or other error!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                default:
                    Task task = new Task(commandGiven);
                    TASKS.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + commandGiven);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}


