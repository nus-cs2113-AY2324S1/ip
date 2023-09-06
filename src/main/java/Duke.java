import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

public class Duke {

    private static List<Task> tasks = new ArrayList<Task>();

    public static void listTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public static void greetings() {
        System.out.println("\tHello! I'm Richard\n");
        System.out.println("\tWhat can I do for you ?\n");
    }


    public static void confirmTaskAdded(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTask.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", tasks.size());
    }

    public static void main(String[] args) {
        greetings();
        Scanner input = new Scanner(System.in);
        String userInput;
        String firstWord;

        do {
            userInput = input.nextLine();
            firstWord = userInput.split(" ")[0];
            switch (firstWord) {
                case "list": {
                    listTasks();
                    break;
                }
                case "mark": {
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    if (index < 0 || index > tasks.size()) {
                        System.out.println("\tPlease enter a valid index.");
                        continue;
                    }
                    tasks.get(index - 1).markAsDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.printf("\t\t [%s] %s\n", tasks.get(index - 1).getStatusIcon(), tasks.get(index - 1).getDescription());
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    if (index < 0 || index > tasks.size()) {
                        System.out.println("Please enter a valid index.");
                        continue;
                    }
                    tasks.get(index - 1).markAsUndone();
                    System.out.println("\tOk! I've marked this task as not done yet:");
                    System.out.printf("\t\t [%s] %s\n", tasks.get(index - 1).getStatusIcon(), tasks.get(index - 1).getDescription());
                    break;
                }
                case "todo": {
                    try {
                        Todo newTask = Todo.fromString(userInput);
                        tasks.add(newTask);
                        confirmTaskAdded(newTask);
                        break;
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                case "deadline": {
                    try {
                        Deadline newTask = Deadline.fromString(userInput);
                        tasks.add(newTask);
                        confirmTaskAdded(newTask);
                        break;
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                case "event": {
                    try {
                        Event newTask = Event.fromString(userInput);
                        tasks.add(newTask);
                        confirmTaskAdded(newTask);
                        break;
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                default: {
                    System.out.println("\t'No command recognized");
                }
            }
        }
        while (!userInput.equals("bye"));
        System.out.println("\tBye hope to see you again\n");
    }
}
