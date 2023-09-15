import task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();

    public Duke() {
        echo("Hello! I'm Mark\nWhat can I do for you?");
    }

    public void echo(String message) {
        System.out.println(Utils.wrapMessage(message));
    }

    public void addToList(Task task) {
        list.add(task);
        echo("added: " + task.getAddMessage());
    }

    public void printList() {
        Task[] listAsArray = list.toArray(new Task[0]);
        String out = "";
        if (listAsArray.length == 0) {
            out = "List is empty.";
        }
        for (int i = 0; i < listAsArray.length; i++) {
            out += (i + 1) + ". " + listAsArray[i].getListText();
        }
        echo(out);
    }

    public void updateTaskStatus(int index, boolean status) throws DukeException {
        if (index > list.size()) {
            throw new DukeException("Index provided is greater than size of list");
        }
        Task task = list.get(index - 1);
        task.setIsComplete(status);
        if (status) {
            echo("Nice! I've marked this task as done:\n" + task.getListText());
        } else {
            echo("OK, I've marked this task as not done yet:\n" + task.getListText());
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (index > list.size() - 1) {
            throw new DukeException("Index out of bounds.");
        }
        list.remove(index);
    }

    public static String getTaskName(String command, String message) throws DukeException {
        if (command.length() == message.trim().length()) {
            throw new DukeException("The description of " + command + " cannot be empty.");
        }
        return message.substring(command.length(),
                message.contains("/") ? message.indexOf("/") : message.length()).trim();
    }

    public static String getTime(String message, String command) throws DukeException {
        if (!message.contains(command)) {
            throw new DukeException("Command " + command + " must be included in description.");
        } else if (command.equals("/from") && !message.contains("/to")) {
            throw new DukeException("Command /to must be included in description of events.");
        }
        String time = message.substring(message.indexOf(command) + command.length(),
                command.equals("/from") ? message.indexOf("/to") : message.length()).trim();
        if (time.isEmpty()) {
            throw new DukeException("Command " + command + " has no description.");
        }
        return time;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        while(!in.equals("bye")) {
            try {
                if (in.equals("list")) {
                    bot.printList();
                } else if (in.startsWith("mark")) {
                    String taskName = getTaskName("mark", in);
                    bot.updateTaskStatus(Integer.parseInt(taskName), true);
                } else if (in.startsWith("unmark")) {
                    String taskName = getTaskName("unmark", in);
                    bot.updateTaskStatus(Integer.parseInt(taskName), false);
                } else if (in.startsWith("todo")) {
                    String taskName = getTaskName("todo", in);
                    bot.addToList(new Todo(taskName));
                } else if (in.startsWith("deadline")) {
                    String taskName = getTaskName("deadline", in);
                    String by = getTime(in, "/by");
                    bot.addToList(new Deadline(taskName, by));
                } else if (in.startsWith("event")) {
                    String taskName = getTaskName("event", in);
                    String from = getTime(in, "/from");
                    String to = getTime(in.substring(in.indexOf("/") + 1), "/to");
                    bot.addToList(new Event(taskName, from, to));
                } else if (in.startsWith("delete")) {
                    String taskName = getTaskName("delete", in);
                    bot.deleteTask(Integer.parseInt(taskName));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                bot.echo(e.getErrorMessage());
            } finally {
                in = sc.nextLine();
            }
        }
        bot.echo("Bye. Hope to see you again soon!");
    }
}
