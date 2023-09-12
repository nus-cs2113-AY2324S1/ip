package elgin;

import elgin.exception.DukeException;
import elgin.task.TaskManager;

import java.util.Scanner;

public class Duke {

    final static String BOT_NAME = "Elgin";
    final static String SEPARATOR = "____________________________________________________________";

    static TaskManager tasks = new TaskManager();

    public static void formatPrint(String[] lines) {
        System.out.println("\t" + SEPARATOR);
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("\t" + SEPARATOR);
    }

    public static void formatPrint(String line) {
        System.out.println("\t" + SEPARATOR);
        System.out.println("\t" + line);
        System.out.println("\t" + SEPARATOR);
    }

    public static void sayGreeting() {
        String[] toPrint = new String[]{
                "Hello! I'm " + BOT_NAME,
                "What can I do for you?"
        };
        formatPrint(toPrint);
    }

    public static void sayBye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    public static boolean handleCommand(String command) throws DukeException {
        int taskNumber;
        String[] parsedCommand = parseCommand(command);
        switch (parsedCommand[0].toLowerCase()) {
        case "bye":
            sayBye();
            return false;
        case "list":
            tasks.listTasks();
            break;
        case "mark":
            try {
                taskNumber = Integer.parseInt(parsedCommand[1]);
                tasks.setTaskIsDone(taskNumber, true);
            } catch (NumberFormatException e) {
                formatPrint("Please enter a task number.");
            }
            break;
        case "unmark":
            try {
                taskNumber = Integer.parseInt(parsedCommand[1]);
                tasks.setTaskIsDone(taskNumber, false);
            } catch (NumberFormatException e) {
                formatPrint("Please enter a task number.");
            }
            break;
        case "todo":
            if (parsedCommand.length < 2) {
                throw new DukeException("OOPS! Description of todo cannot be empty");
            }
            tasks.addTask(parsedCommand[1]);
            break;
        case "deadline":
            String[] deadlineCommands = parsedCommand[1].split(" /by ");
            if (deadlineCommands.length < 2) {
                throw new DukeException("Usage: deadline <task> /by <deadline>");
            }
            tasks.addTask(deadlineCommands[0], deadlineCommands[1]);
            break;
        case "event":
            String[] eventCommands = parsedCommand[1].split(" /from ");
            if (eventCommands.length < 2) {
                throw new DukeException("Usage: event <task> /from <time> /to <time>");
            }
            String[] fromTo = eventCommands[1].split(" /to ");
            if (fromTo.length < 2) {
                throw new DukeException("Usage: event <task> /from <time> /to <time>");
            }
            tasks.addTask(eventCommands[0], fromTo[0], fromTo[1]);
            break;
        default:
            throw new DukeException("Sorry I do not understand your command");
        }
        return true;
    }

    public static String[] parseCommand(String command) {
        String[] commandArray = command.split(" ", 2);
        String arg1 = commandArray[0].toLowerCase();
        boolean isMarkTask = arg1.equals("mark") || arg1.equals("unmark");
        boolean isAddTask = arg1.equals("todo") || arg1.equals("event") || arg1.equals("deadline");
        if (isMarkTask || isAddTask) {
            return commandArray;
        } else {
            return new String[]{command};
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        sayGreeting();
        String command;
        boolean isContinue = true;
        while (isContinue) {
            command = scanner.nextLine();
            try {
                isContinue = handleCommand(command);
            } catch (DukeException e) {
                formatPrint(e.getMessage());
            }
        }
    }
}