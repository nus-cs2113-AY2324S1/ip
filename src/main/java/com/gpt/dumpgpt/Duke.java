package com.gpt.dumpgpt;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Deadline;
import com.gpt.dumpgpt.task.Event;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class Duke {
    private static boolean ended = false;
    private final static int INVALID_TASK_NUM = -1;
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static HashMap<String, Function<Command, Void>> COMMANDS = new HashMap<>();

    public static void main(String[] args) {
        registerCommands();
        greet();
        while (!ended) {
            String userInput = getInput();
            Command userCommand = new Command(userInput);
            userCommand.parse();

            if (userCommand.isEmpty()) {
                printWrapped("Please provide an input!");
                continue;
            }

            Function<Command, Void> command = COMMANDS.get(userCommand.getCommandVerb());
            if (command == null) {
                printWrapped("Unknown command...");
                continue;
            }

            command.apply(userCommand);
        }
    }

    public static void registerCommands() {
        COMMANDS.put("bye", (e) -> bye());
        COMMANDS.put("list", (e) -> listTasksFlow());
        COMMANDS.put("mark", Duke::markTaskFlow);
        COMMANDS.put("unmark", Duke::unmarkTaskFlow);
        COMMANDS.put("deadline", Duke::addTaskFlow);
        COMMANDS.put("event", Duke::addTaskFlow);
        COMMANDS.put("todo", Duke::addTaskFlow);
    }

    public static String getInput() {
        System.out.print("User: ");
        return SCANNER.nextLine();
    }

    public static Void addTaskFlow(Command command) {
        Task newTask = null;

        switch (command.getCommandVerb()) {
        case "deadline":
            newTask = new Deadline(command.getArguments(), command.getOptions("by"));
            break;
        case "event":
            newTask = new Event(command.getArguments(), command.getOptions("from"), command.getOptions("to"));
            break;
        case "todo":
            newTask = new Todo(command.getArguments());
        }

        if (newTask != null && newTask.isValid()) {
            Task.addTask(newTask);
            printAddedTask(newTask);
        } else {
            printWrapped("Failed to add new task...");
        }

        return null;
    }

    private static void printAddedTask(Task task) {
        int tasksCount = Task.getTasks().size();
        String taskSummary = String.format(
                "You now have %d %s in the list!",
                tasksCount,
                (tasksCount > 1) ? "tasks" : "task"
        );
        printWrapped(new String[]{
                "Great! I've added the following task:",
                "\t" + task.toString(),
                taskSummary
        });
    }

    public static Void listTasksFlow() {
        ArrayList<Task> tasks = Task.getTasks();
        int idx = 1;
        ProgramConstants.printSeparator();
        for (Task task : tasks) {
            System.out.printf("%d. %s\n", idx++, task);
        }
        ProgramConstants.printSeparator();
        return null;
    }

    public static Void markTaskFlow(Command command) {
        int taskNumber = parsePositiveNumber(command.getArguments());
        if (taskNumber == INVALID_TASK_NUM) {
            printWrapped("Failed to mark task as done...");
            return null;
        }

        Task task = Task.getTask(--taskNumber);
        if (task == null) {
            printWrapped("Failed to mark task as done...");
            return null;
        }

        task.markDone();
        printWrapped(new String[]{
                "Nice I've marked this task as done:",
                task.toString()
        });
        return null;
    }

    public static Void unmarkTaskFlow(Command command) {
        int taskNumber = parsePositiveNumber(command.getArguments());
        if (taskNumber == INVALID_TASK_NUM) {
            printWrapped("Failed to mark task as undone...");
            return null;
        }

        Task task = Task.getTask(--taskNumber);
        if (task == null) {
            printWrapped("Failed to mark task as undone...");
            return null;
        }

        task.unmarkDone();
        printWrapped(new String[]{
                "OK, I've marked this task as not done yet:",
                task.toString()
        });
        return null;
    }

    public static int parsePositiveNumber(String number) {
        int taskNumber = INVALID_TASK_NUM;

        try {
            taskNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return taskNumber;
        }

        if (taskNumber <= 0) {
            return INVALID_TASK_NUM;
        }
        return taskNumber;
    }

    public static void printWrapped(String printOut) {
        ProgramConstants.printSeparator();
        System.out.println(printOut);
        ProgramConstants.printSeparator();
    }

    public static void printWrapped(String[] printOut) {
        ProgramConstants.printSeparator();
        for (String line : printOut) {
            System.out.println(line);
        }
        ProgramConstants.printSeparator();
    }

    public static void greet() {
        printWrapped(new String[]{
                String.format("Hello I'm %s", ProgramConstants.BOT_NAME),
                "What can I do for you?"
        });
    }

    public static Void bye() {
        printWrapped("Bye. Hope to see you again soon!");
        ended = true;
        return null;
    }
}
