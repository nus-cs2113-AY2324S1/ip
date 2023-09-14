package duke;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;
    public static void main(String[] args) {
        printGreeting();

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

    private static void handleCommand(String line) throws DukeException {
        int divider = line.indexOf(" ");
        if (divider == -1) {
            throw new DukeException("Sorry! Not sure what you mean");
        }

        if (line.contains("mark")) {
            int idx = Integer.parseInt(line.substring(divider + 1)) - 1;
            if (idx < 0 || tasks[idx] == null) {
                throw new DukeException("Sorry! That's not a valid task");
            }

            markTask(idx, line.startsWith("mark"));
            return;
        }
        if (line.startsWith("todo")){
            String description = line.substring(divider + 1);

            addTask(new Todo(description));
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
        tasks[index].setStatus(isDone);
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks[index].getFormattedTask());
    }

    private static void printGreeting() {
        System.out.println("Hello! I'm KevBot");
        System.out.println("What can I do for you?");
    }

    public static void printTasks() {
        for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getFormattedTask());
        }
    }

    public static void addTask(Task task) throws DukeException {
        if (numTasks == tasks.length) {
            throw new DukeException("Sorry. The task list is all full");
        }

        tasks[numTasks] = task;
        numTasks++;
        System.out.println("Got it. I've added this task:\n" + task.getFormattedTask() + "\nNow you have " + numTasks + " tasks in the list.");
    }
}
