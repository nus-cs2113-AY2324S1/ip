import Soccat.*;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Soccat {

    static ArrayList<Task> tasks = new ArrayList<>();
    static final String[] COMMANDS = {"bye","list","todo","deadline","event","mark","unmark"};

    public static void addTask(String command, String type) {
        String task = command.replace(type, "").strip();
        switch(type) {
        case "todo":
            if (task.isEmpty()) {
                System.out.println("Oops, the task for todo cannot be blank!");
                return;
            }
            tasks.add(new Todo(task));
            break;
        case "deadline":
            try {
                String[] deadlineTokens = task.split(" /by ");
                tasks.add(new Deadline(deadlineTokens[0], deadlineTokens[1]));
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops, you have missing fields for creating a deadline!");
                System.out.println("Please try deadline <task> /by <deadline>");
                return;
            }
        case "event":
            try {
                String[] eventTokens = task.split(" /");
                String from = eventTokens[1].replace("from ", "");
                String by = eventTokens[2].replace("to ", "");
                tasks.add(new Event(eventTokens[0], from, by));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops, you have missing fields for creating an event!");
                System.out.println("Please try event <task> /from <start> /to <end>");
                return;
            }
            break;
        }
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void listTasks() {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            System.out.println("Great, You have no tasks for now!");
            return;
        }
        int taskIndex;
        for(int i=0; i<taskCount; i++) {
            // Add 1 for 1-based indexing
            taskIndex = i + 1;
            System.out.print(taskIndex);
            System.out.println(". " + tasks.get(i));
        }
    }

    public static void toggleDone(String taskNumber, boolean isDone) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            System.out.println("No tasks to mark for now, you may take a break!");
            return;
        }
        try {
            int taskId = Integer.parseInt(taskNumber);
            if (taskId > taskCount) {
                System.out.println("Oops, there are only " + taskCount + " tasks now!");
                return;
            }
            // Subtract 1 for array index
            int taskIndex = taskId - 1;
            Task task = tasks.get(taskIndex);
            task.setDone(isDone);
            System.out.println(tasks.get(taskIndex));
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task number out of range!");
        }
    }

    public static void testCommandInput(String commandInput) throws SoccatException {
        for (String command : COMMANDS) {
            if (commandInput.equals(command)) {
                return;
            }
        }
        throw new SoccatException();
    }

    public static void processCommand(String line) {
        Scanner textIn = new Scanner(System.in);
        String commandInput = "";
        while (!commandInput.equals("bye")) {
            commandInput = textIn.nextLine();
            String[] tokens = commandInput.split(" ");
            System.out.println(line);
            // Test command for compatability
            try {
                testCommandInput(tokens[0]);
            } catch (SoccatException e) {
                System.out.println("Oops, your command is not recognized, please try again!");
                System.out.println(line);
                continue;
            }
            switch (tokens[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                listTasks();
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(commandInput, tokens[0]);
                break;
            case "mark":
                toggleDone(tokens[1], true);
                break;
            case "unmark":
                toggleDone(tokens[1], false);
                break;
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm soccat!\nWhat can I do for you?\n" + line);
        processCommand(line);
    }
}
