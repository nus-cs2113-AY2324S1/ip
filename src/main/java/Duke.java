import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> taskList = new LinkedList<>();
        greetToUsers();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {

                scanTheList(taskList);

            } else if (line.startsWith("mark") || line.startsWith("unmark")) {

                markTheTask(line, taskList);

            } else {
                String[] words = line.split(" ");
                String firstWord = words[0];

                switch (firstWord) {

                case "deadline":
                    handleDeadline(taskList, line);
                    break;
                case "todo":
                    handleTodo(taskList, words);
                    break;
                case "event":
                    handleEvent(taskList, line);
                    break;
                default:
                    handleDefault(taskList, line);

                }

                feedbackOfTheExecution(taskList);
            }

            line = in.nextLine();

        }

        byeToUsers();
    }

    private static void feedbackOfTheExecution(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.get(taskList.size()-1).toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDefault(List<Task> taskList, String line) {
        taskList.add(new Task(line));
        System.out.println("     added: " + line);
    }

    private static void handleEvent(List<Task> taskList, String line) {
        String[] userfulInfo = Event.handleInputForEvent(line);
        taskList.add(new Event(userfulInfo[0], userfulInfo[1]));
    }

    private static void handleTodo(List<Task> taskList, String[] words) {
        String task = "";
        for (int i = 1; i < words.length; i++) {
            task += words[i] + " ";
        }
        taskList.add(new Todo(task.trim()));
    }

    private static void handleDeadline(List<Task> taskList, String line) {
        String by = line.split("/")[1];
        String description = line.split("/")[0].replace("deadline", "").trim();
        taskList.add(new Deadline(description, by.replace("by", "").trim()));
    }

    public static void greetToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void byeToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void scanTheList(List taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i+1)  + "."+ taskList.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTheTask(String line, List<Task> taskList) {
        System.out.println("____________________________________________________________");
        String[] words = line.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        if (line.startsWith("mark")) {
            taskList.get(index).setDone(true);
            System.out.println("     Nice! I've marked this task as done:");
        } else {
            taskList.get(index).setDone(false);
            System.out.println("     OK, I've marked this task as not done yet:");
        }
        System.out.println("       " + taskList.get(index).toString());
        System.out.println("____________________________________________________________");

    }
}
