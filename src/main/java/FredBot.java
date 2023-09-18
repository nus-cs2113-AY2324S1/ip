import fredbot.error.*;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FredBot {

    public static final String GREETING = "     Hello! I'm Fredbot\n" +
            "     What can I do for you?";
    public static final String FAREWELL = "     Bye. Hope to see you again soon!";
    public static final String INDENT = "    ";
    public static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:\n";
    public static final String UNMARK_TASK_MESSAGE = "Nice! I've marked this task as not done yet:\n";
    public static final String DIVIDER = "    ____________________________________________________________\n";
    public static final String TASK_LIST_MESSAGE = "Here are the tasks in your list\n";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TODO_ERROR_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String DEADLINE_ERROR_MESSAGE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EVENT_ERROR_MESSAGE = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String MARK_ERROR_MESSAGE = "This task does not exist!";
    public static final String COMMAND_DELETE = "delete";
    public static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n";
    public static final String TASK_FILE_PATH = "./data/tasks.txt";
    public static final String WRITE_FILE_ERROR_MESSAGE = "Could not write to file. Exiting Application...";
    public static final String FIND_FILE_ERROR_MESSAGE = "Could not find file to load. Exiting Application...";

    public static void loadTasksfromfile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(TASK_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            switch (task.charAt(1)) {
            case 'T':
                tasks.add(new Todo(task.substring(6).trim()));
                tasks.get(Task.getNumTask()).setDone(task.charAt(4) == 'X');
                Task.setNumTask(Task.getNumTask()+1);
                break;
            case 'D':
                String[] argumentsDeadline = task.substring(6).split("by:");
                tasks.add(new Deadline(argumentsDeadline[0].trim(),argumentsDeadline[1].trim()));
                tasks.get(Task.getNumTask()).setDone(task.charAt(4) == 'X');
                Task.setNumTask(Task.getNumTask()+1);
                break;
            case 'E':
                String[] argumentsEvent = task.substring(6).split("to:|from:");
                tasks.add(new Event(argumentsEvent[0].trim(),
                        argumentsEvent[1].trim(), argumentsEvent[2].trim()));
                tasks.get(Task.getNumTask()).setDone(task.charAt(4) == 'X');
                Task.setNumTask(Task.getNumTask()+1);
                break;
            }
        }
    }
    public static void addTaskstoFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH);
        StringBuilder tasksText = new StringBuilder();
        for (int i = 0; i < Task.getNumTask(); i++) {
            tasksText.append(tasks.get(i).toString()).append(System.lineSeparator());
        }
        fw.write(String.valueOf(tasksText));
        fw.close();
    }

    public static void addTodo(ArrayList<Task> tasks, String task) throws FredBotTodoErrorException, IOException {
        if (task.isEmpty()) {
            throw new FredBotTodoErrorException();
        }
        int numTask = Task.getNumTask();
        tasks.add(new Todo(task.trim()));
        printAddTask(INDENT + tasks.get(numTask).toString() + "\n");
        Task.setNumTask(numTask+1);
        addTaskstoFile(tasks);
    }

    public static void addDeadline(ArrayList<Task> tasks, String task) throws FredBotDeadlineErrorException, IOException {
        if (task.isEmpty()) {
            throw new FredBotDeadlineErrorException();
        }
        int numTask = Task.getNumTask();
        String[] arguments = task.split("/by");
        if (arguments.length != 2) {
            throw new FredBotDeadlineErrorException();
        }
        tasks.add(new Deadline(arguments[0].trim(),arguments[1].trim()));
        printAddTask(INDENT + tasks.get(numTask).toString() + "\n");
        Task.setNumTask(numTask+1);
        addTaskstoFile(tasks);
    }

    public static void addEvent(ArrayList<Task> tasks, String task) throws FredBotEventErrorException, IOException {
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
        addTaskstoFile(tasks);
    }

    public static void deleteTask(ArrayList<Task> tasks, int index) throws FredBotDeleteErrorException, IOException{
        if (index < 1 || index > Task.getNumTask()) {
            throw new FredBotDeleteErrorException();
        }
        printRemoveTask(tasks.remove(index - 1).toString() + "\n");
        Task.setNumTask(Task.getNumTask()-1);
        addTaskstoFile(tasks);
    }

    public static void changeStatus(ArrayList<Task> tasks, boolean mark, int index) throws FredBotMarkErrorException,IOException {
        if (index < 1 || index > Task.getNumTask()) {
            throw new FredBotMarkErrorException();
        }
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
        addTaskstoFile(tasks);
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

    public static void printRemoveTask(String message) {
        printMessage(INDENT + REMOVE_TASK_MESSAGE + INDENT + message + INDENT +
                "Now you have " + (Task.getNumTask() - 1) + " tasks in the list\n");
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
        try {
            loadTasksfromfile(tasks);
        } catch (FileNotFoundException e) {
            printMessage(INDENT + FIND_FILE_ERROR_MESSAGE);
            System.exit(0);
        }


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
                } else if (line.startsWith(COMMAND_DELETE)) {
                    int index = Integer.parseInt(line.substring(6).trim());
                    deleteTask(tasks, index);
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
            } catch (FredBotMarkErrorException | FredBotDeleteErrorException e) {
                printMessage(INDENT + MARK_ERROR_MESSAGE);
            } catch (IOException e) {
                printMessage(INDENT + WRITE_FILE_ERROR_MESSAGE);
                System.exit(0);
            }

            line = in.nextLine();
        }
        
        printMessage(FAREWELL);
    }
}
