import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();

    public Duke() {
        respond("Hello! I'm Mark\nWhat can I do for you?");
    }

    public void addToList(Task task) {
        list.add(task);
    }
    public void printList() {
        Task[] listAsArray = list.toArray(new Task[0]);
        String out = "";
        for (int i = 0; i < listAsArray.length; i++) {
            out += (i == 0 ? "" : "\n") +
                    (i + 1) +
                    ". [" +
                    (listAsArray[i].getIsComplete() ? "X" : " ") +
                    "] " +
                    listAsArray[i].getName();
        }
        respond(out);
    }
    public void updateTask(String name, boolean status) {
        for (Task task : list) {
            if (task.getName().equals(name)) {
                task.setComplete(status);
            }
        }
    }
    public void respond(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n" +
                "____________________________________________________________");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                bot.printList();
            } else if (in.startsWith("mark")) {
                String taskName = in.substring("mark".length() + 1);
                bot.updateTask(taskName, true);
                bot.respond("Nice! I've marked this task as done:\n" +
                        "[X] " + taskName);
            } else if (in.startsWith("unmark")) {
                String taskName = in.substring("unmark".length() + 1);
                bot.updateTask(taskName, false);
                bot.respond("OK, I've marked this task as not done yet:\n" +
                        "[ ] " + taskName);
            } else {
                bot.addToList(new Task(in));
                bot.respond("added: " + in);
            }
            in = sc.nextLine();
        }
        bot.respond("Bye. Hope to see you again soon!");
    }
}
