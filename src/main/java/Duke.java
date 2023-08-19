import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String chatbotName="Andrew Tate";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        label:
        while (true){
            String commandGiven = myScanner.nextLine();
            String[] splitCommand = commandGiven.split("\\s+");
            switch (splitCommand[0]) {
                case "bye":
                    break label;
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + 1 + ".[" + task.getStatusIcon() + "] " + task.description);
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    try{
                        int taskIndex = Integer.parseInt(splitCommand[1]);
                        Task taskToMark = tasks.get(taskIndex-1);
                        taskToMark.markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Marked this task as done:");
                        System.out.println("[" + taskToMark.getStatusIcon() + "] " + taskToMark.description);
                        System.out.println("____________________________________________________________");

                    }catch(Exception e){
                        System.out.println("Invalid index.");
                    }
                    break;

                case "unmark":
                    try{
                        int taskIndex = Integer.parseInt(splitCommand[1]);
                        Task taskToUnmark = tasks.get(taskIndex-1);
                        taskToUnmark.markAsUndone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Marked this task as undone:");
                        System.out.println("[" + taskToUnmark.getStatusIcon() + "] " + taskToUnmark.description);
                        System.out.println("____________________________________________________________");

                    }catch(Exception e){
                        System.out.println("Invalid index.");
                    }
                    break;

                default:
                    Task task = new Task(commandGiven);
                    tasks.add(task);
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


