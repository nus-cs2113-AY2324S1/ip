import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();

    public Duke() {
        respond("Hello! I'm Mark\nWhat can I do for you?");
    }

    public void addToList(Task task) {
        list.add(task);
        respond("added: " + task.addMessage());
    }

    public void printList() {
        Task[] listAsArray = list.toArray(new Task[0]);
        String out = "";
        for (int i = 0; i < listAsArray.length; i++) {
            out += (i == 0 ? "" : "\n") + (i + 1) + ". " + listAsArray[i].listText();
        }
        respond(out);
    }

    public void updateTask(int index, boolean status) {
        Task task = list.get(index - 1);
        task.setIsComplete(status);
        if (status) {
            respond("Nice! I've marked this task as done:\n" +
                    task.listText());
        } else {
            respond("OK, I've marked this task as not done yet:\n" +
                    task.listText());
        }
    }

    public void respond(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________");
    }

    public static String getTask(String command, String message) {
        return message.substring(command.length() + 1,
                (message.contains("/") ? message.indexOf("/") : message.length())).trim();
    }

    public static String getTime(String message, String command) {
        return message.substring(message.indexOf(command) + command.length() + 1,
                (command.equals("/from") ? message.indexOf("/to") : message.length())).trim();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                bot.printList();
            } else if (in.startsWith("mark")) {
                String taskName = getTask("mark", in);
                bot.updateTask(Integer.parseInt(taskName), true);
            } else if (in.startsWith("unmark")) {
                String taskName = getTask("unmark", in);
                bot.updateTask(Integer.parseInt(taskName), false);
            } else if (in.startsWith("todo")) {
                String taskName = getTask("todo", in);
                bot.addToList(new Todo(taskName));
            } else if (in.startsWith("deadline")) {
                String taskName = getTask("deadline", in);
                String by = getTime(in, "/by");
                bot.addToList(new Deadline(taskName, by));
            } else if (in.startsWith("event")) {
                String taskName = getTask("event", in);
                String from = getTime(in, "/from");
                String to = getTime(in.substring(in.indexOf("/") + 1), "/to");
                bot.addToList(new Event(taskName, from, to));
            } else {
                bot.addToList(new Task(in));
            }
            in = sc.nextLine();
        }
        bot.respond("Bye. Hope to see you again soon!");
    }
}
