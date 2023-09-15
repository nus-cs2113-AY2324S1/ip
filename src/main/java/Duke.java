import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private final String FILE_PATH = "./data/list.txt";

    protected static ArrayList<Task> list = new ArrayList<>();

    public Duke() {
        echo("Hello! I'm Mark\nWhat can I do for you?");
        loadSave();
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
            out += (i == 0 ? "" : "\n") + (i + 1) + ". " + listAsArray[i].getListText();
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

    public void save() {
        File f = new File(FILE_PATH);
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(FILE_PATH);
            String save = "";
            for (int i = 0; i < Duke.list.size(); i++) {
                save += Duke.list.get(i).getSaveString() + "\n";
            }
            fw.write(save);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadSave() {
        File f = new File(FILE_PATH);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" \\| ");
                boolean isMark = line[1].equals("1");
                String taskName = line[2];
                switch (line[0]) {
                    case "T": {
                        Task task = new Todo(taskName);
                        task.setIsComplete(isMark);
                        list.add(task);
                        break;
                    }
                    case "D": {
                        String by = line[3];
                        Task task = new Deadline(taskName, by);
                        task.setIsComplete(isMark);
                        list.add(task);
                        break;
                    }
                    case "E": {
                        String from = line[3].split("-")[0];
                        String to = line[3].split("-")[1];
                        Task task = new Event(taskName, from, to);
                        task.setIsComplete(isMark);
                        list.add(task);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ignored) {
        }
    }

    public void clearList() {
        list = new ArrayList<>();
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
                } else if (in.startsWith("clear")) {
                    bot.clearList();
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                bot.echo(e.getErrorMessage());
            } finally {
                in = sc.nextLine();
            }
        }
        bot.save();
        bot.echo("Bye. Hope to see you again soon!");
    }
}
