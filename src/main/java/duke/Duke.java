package duke;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        printGreeting();
        loadTasks();

        Scanner in = new Scanner(System.in);
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            if (line.equals("list")) {
                printTasks();
            } else {
                try {
                    handleCommand(line);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void loadTasks() {
        File f = new File("duke.txt");
        try {
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parsed = line.split("\\|");
                String taskType = parsed[0];
                String description = parsed[2];

                switch (taskType) {
                case "T":
                    tasks.add(new Todo(description));
                    break;
                case "D":
                    String by = parsed[3];
                    tasks.add(new Deadline(description, by));
                    break;
                case "E":
                    String from = parsed[3];
                    String to = parsed[4];
                    tasks.add(new Event(description, from, to));
                    break;
                default:
                    System.out.println("Unknown task type detected");
                    break;
                }

                if (parsed[1].equals("true")) {
                    tasks.get(tasks.size() - 1).setStatus(true);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleCommand(String line) throws DukeException {
        int divider = line.indexOf(" ");
        if (divider == -1) {
            throw new DukeException("Sorry! Not sure what you mean");
        }


        if (line.startsWith("todo")){
            String description = line.substring(divider + 1);

            addTask(new Todo(description));
            return;
        }

        if (line.contains("mark") || line.startsWith("delete")) {
            int idx = Integer.parseInt(line.substring(divider + 1)) - 1;
            if (idx < 0 || tasks.get(idx) == null) {
                throw new DukeException("Sorry! That's not a valid task");
            }

            if (line.contains("mark")) {
                markTask(idx, line.startsWith("mark"));
            } else {
                removeTask(idx);
            }
            return;
        }

        HashMap<String, String> parameters = parseParameters(line);
        String description = parameters.get("description");
        if (description == null) {
            throw new DukeException("Sorry! Please provide a valid description");
        }
        if (line.startsWith("deadline")) {
            String by = parameters.get("by");
            if (by == null) {
                throw new DukeException("Sorry! Please provide a valid `by`");
            }
            addTask(new Deadline(description, by));
        } else if (line.startsWith("event")) {
            String from = parameters.get("from");
            String to = parameters.get("to");
            if (from == null || to == null) {
                throw new DukeException("Sorry! Please provide a valid `from` and/or `to`");
            }
            addTask(new Event(description, from, to));
        } else {
            throw new DukeException("Sorry! Please enter a valid command");
        }
    }

    private static HashMap<String, String> parseParameters(String line) throws DukeException {
        HashMap<String, String> fieldToValue = new HashMap<>();

        int startDescription = line.indexOf(" ");
        int endOfDescription = line.indexOf(" /");
        if (startDescription == -1 || endOfDescription == -1) {
            throw new DukeException("Sorry! Not sure what you mean");
        }
        fieldToValue.put("description", line.substring(startDescription + 1, endOfDescription));

        String[] splitParams = line.split(" /");
        for (int i = 1; i < splitParams.length; i++) {
            String rawParam = splitParams[i];
            int divider = rawParam.indexOf(" ");
            if (divider == -1) {
                throw new DukeException("Sorry! Please enter valid inputs");
            }

            String field = rawParam.substring(0, divider);
            String value = rawParam.substring(divider + 1);
            fieldToValue.put(field, value);
        }

        return fieldToValue;
    }

    public static void markTask(int index, boolean isDone) {
        tasks.get(index).setStatus(isDone);
        saveTasks();
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks.get(index).getFormattedTask());
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm KevBot");
        System.out.println("What can I do for you?");
    }

    public static void printTasks() {
        for (int i = 0; i < tasks.size() && tasks.get(i) != null; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getFormattedTask());
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        saveTasks();
        System.out.println("Got it. I've added this task:\n" + task.getFormattedTask() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void removeTask(int idx) {
        Task removedTask = tasks.remove(idx);
        saveTasks();
        System.out.println("Got it. I've removed this task:\n" + removedTask.getFormattedTask() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void saveTasks() {
        StringBuilder serialized = new StringBuilder();
        for (Task task : tasks) {
            serialized.append(task.getSerializedString()).append("\n");
        }

        File f = new File("duke.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(serialized.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
