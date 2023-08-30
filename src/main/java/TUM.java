import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TUM {
    public static void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        List<Task> taskList = new LinkedList<>();
        greetings();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.println("     " + (i+1) + ".[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                if (line.startsWith("mark")) {
                    taskList.get(index).setDone(true);
                    System.out.println("____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       [" + taskList.get(index).getStatusIcon() + "] " + taskList.get(index).getDescription());
                    System.out.println("____________________________________________________________");
                } else {
                    taskList.get(index).setDone(false);
                    System.out.println("____________________________________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       [" + taskList.get(index).getStatusIcon() + "] " + taskList.get(index).getDescription());
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                taskList.add(new Task(line));
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
