import fredbot.error.FredBotCommandErrorException;
import fredbot.error.FredBotDeadlineErrorException;
import fredbot.error.FredBotEventErrorException;
import fredbot.error.FredBotTodoErrorException;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FredBot {

    public static final String GREETING = "     Hello! I'm Fredbot\n" +
            "     What can I do for you?";
    public static final String FAREWELL = "     Bye. Hope to see you again soon!";
    public static final String INDENT = "    ";
    public static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:\n";
    public static final String UNMARK_TASK_MESSAGE = "Nice! I've marked this task as not done yet:\n";
    public static final String DIVIDER = "    ____________________________________________________________\n";
    public static final int MAX_NUM_TASKS = 100;
    public static final String TASK_LIST_MESSAGE = "Here are the tasks in your list\n";
    public static final String EVENT_FROM_PREFIX = " /from";
    public static final String EVENT_TO_PREFIX = " /to";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TODO_ERROR_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String DEADLINE_ERROR_MESSAGE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EVENT_ERROR_MESSAGE = "☹ OOPS!!! The description of a event cannot be empty.";

    public static void addTodo(ArrayList<Task> tasks, String task) throws FredBotTodoErrorException {
        if (task.isEmpty()) {
            throw new FredBotTodoErrorException();
        }
        int numTask = Task.getNumTask();
        tasks.add(new Todo(task));
        printAddTask(INDENT + tasks.get(numTask).toString() + "\n");
        Task.setNumTask(numTask+1);
    }

    public static void addDeadline(ArrayList<Task> tasks, String task) throws FredBotDeadlineErrorException {
        if (task.isEmpty()) {
            throw new FredBotDeadlineErrorException();
        }
        int numTask = Task.getNumTask();
        String[] arguments = task.split("/by");
        if (arguments.length != 2) {
            throw new FredBotDeadlineErrorException();
        }
        tasks.add(new Deadline(arguments[0],arguments[1]));
        printAddTask(INDENT + tasks.get(numTask).toString() + "\n");
        Task.setNumTask(numTask+1);
    }

    public static void addEvent(ArrayList<Task> tasks, String task) throws FredBotEventErrorException {
        if (task.isEmpty()) {
            throw new FredBotEventErrorException();
        }
        int numTask = Task.getNumTask();
        String[] arguments = task.split("/to|/from");
        if (arguments.length != 3) {
            throw new FredBotEventErrorException();
        }
        tasks.add(new Event(arguments[0].trim(), arguments[1].trim(), arguments[2].trim()));
        printAddTask(INDENT + tasks.get(numTask).toString() + "\n");
        Task.setNumTask(numTask+1);
    }

    public static void changeStatus(ArrayList<Task> tasks, boolean mark, int index) {
        tasks.get(index - 1).setDone(mark);
        String message;
        if (mark) {
            message = INDENT + MARK_TASK_MESSAGE;
            message += INDENT + "[X] " + tasks.get(index - 1).getTaskDesc();
        } else {
            message = INDENT + UNMARK_TASK_MESSAGE;
            message += INDENT + "[ ] " + tasks.get(index - 1).getTaskDesc();
        }
        printMessage(message);
    }
    public static void printTasks(ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(INDENT).append(TASK_LIST_MESSAGE);
        int numTask = Task.getNumTask();
        for (int i = 0; i < numTask; i++) {
            String number = (i + 1) + ".";
            taskList.append(INDENT).append(number).append(tasks.get(i).toString()).append("\n"); // Can be formatted
        }
        printMessage(taskList.toString());
    }

    public static void printAddTask(String message) {
        printMessage(INDENT + message + INDENT +
                "Now you have " + (Task.getNumTask() +1) + " tasks in the list\n");
    }
    public static void printMessage(String message) {
        System.out.print(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
    public static void main(String[] args) {
        // Task[] tasks = new Task[MAX_NUM_TASKS];
        ArrayList<Task> tasks = new ArrayList<>();

        printMessage(GREETING);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    printTasks(tasks);
                } else if (line.startsWith(COMMAND_MARK)) {
                    int index = Integer.parseInt(line.substring(5).trim());
                    changeStatus(tasks, true, index);
                } else if (line.startsWith(COMMAND_UNMARK)) {
                    int index = Integer.parseInt(line.substring(7).trim());
                    changeStatus(tasks, false, index);
                } else if (line.startsWith(COMMAND_ADD_TODO)){
                    addTodo(tasks, line.replace(COMMAND_ADD_TODO, "").trim());
                } else if (line.startsWith(COMMAND_ADD_DEADLINE)) {
                    addDeadline(tasks, line.replace(COMMAND_ADD_DEADLINE, "").trim());
                } else if (line.startsWith(COMMAND_ADD_EVENT)) {
                    addEvent(tasks, line.replace(COMMAND_ADD_EVENT, "").trim());
                } else {
                    throw new FredBotCommandErrorException();
                }
            } catch (FredBotCommandErrorException e) {
                printMessage(INDENT + COMMAND_ERROR_MESSAGE);
            } catch (FredBotDeadlineErrorException e) {
                printMessage(INDENT + DEADLINE_ERROR_MESSAGE);
            } catch (FredBotTodoErrorException e) {
                printMessage(INDENT + TODO_ERROR_MESSAGE);
            } catch (FredBotEventErrorException e) {
                printMessage(INDENT + EVENT_ERROR_MESSAGE);
            }

            line = in.nextLine();
        }
        
        printMessage(FAREWELL);
    }
}
