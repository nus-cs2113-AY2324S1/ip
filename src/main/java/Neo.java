import java.util.Scanner;
public class Neo {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] list = new Task[100];
        for (int i = 0; i < 100; i++) {
            list[i] = new Task("");
        }
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                for (int i = 0; i < Task.getTotalTasks(); i++) {
                    System.out.print((i + 1) + ". ");
                    System.out.print("[" + list[i].getStatusIcon() + "] ");
                    System.out.println(list[i].description);
                }
                line = in.nextLine();
            }
            else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int listIndex = Integer.parseInt(words[1]) - 1;
                list[listIndex].setDone(true);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("    [X] " + list[listIndex].description);
                line = in.nextLine();
            }
            else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int listIndex = Integer.parseInt(words[1]) - 1;
                list[listIndex].setDone(false);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("    [ ] " + list[listIndex].description);
                line = in.nextLine();
            }
            else {
                list[Task.getTotalTasks()].description = line;
                Task.incrementTotalTasks();
                System.out.println("added: " + line);
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
