package linguobot.command;

import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.Task;
import linguobot.task.Todo;

import java.util.ArrayList;

import static linguobot.file.TaskFile.saveTaskListToFile;

public class CommandResponse {
    public static void printLine() {
        System.out.println("-------------------------");
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i));
        }
        printLine();
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, int index) throws LinguoBotException {
        if (index >= 0 && index < taskList.size()) {
            if (taskList.get(index).getStatusIcon().equals("X")) {
                throw new LinguoBotException("Task has already been marked.");
            } else {
                taskList.get(index).markAsDone();
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index));
                printLine();
                saveTaskListToFile(taskList);
            }
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1));
        }
    }

    public static void markTaskAsUndone(ArrayList<Task> taskList, int index) throws LinguoBotException {
        if (index >= 0 && index < taskList.size() && taskList.get(index) != null) {
            if (taskList.get(index).getStatusIcon().equals(" ")) {
                throw new LinguoBotException("Task has already been unmarked.");
            } else {
                taskList.get(index).markAsUndone();
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskList.get(index));
                printLine();
                saveTaskListToFile(taskList);
            }
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1));
        }
    }

    public static void deleteTask(ArrayList<Task> taskList, int index) throws LinguoBotException {
        if (index >= 0 && index < taskList.size()) {
            printLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.get(index));
            System.out.println("Now you have " + (taskList.size() - 1) + " task(s) in the list.");
            printLine();
            taskList.remove(index);
            saveTaskListToFile(taskList);
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1) + " to delete task.");
        }
    }
    public static void addTask(String line, ArrayList<Task> taskList) throws LinguoBotException{
        if (line.startsWith("todo")) {
            if (line.substring(4).isEmpty()) {
                throw new LinguoBotException("Todo description cannot be empty.");
            }
            taskList.add(new Todo(line.substring(4)));
        } else if (line.startsWith("deadline")) {
            int indexBy = line.indexOf("by");
            if (line.substring(8).isEmpty()) {
                throw new LinguoBotException("Deadline description cannot be empty.");
            }
            if (indexBy == -1) {
                throw new LinguoBotException("Invalid input. Please include 'by' for deadlines.");
            }
            taskList.add(new Deadline(line.substring(8, indexBy - 1), line.substring(indexBy + 2)));
        } else if (line.startsWith("event")) {
            int indexFrom = line.indexOf("from");
            int indexTo = line.indexOf("to", indexFrom);
            if (line.substring(5).isEmpty()) {
                throw new LinguoBotException("Event description cannot be empty.");
            }
            if (indexFrom == -1 || indexTo == -1) {
                throw new LinguoBotException("Invalid input. Please include both 'from' and 'to' for events.");
            }
            taskList.add(new Event(line.substring(5, indexFrom - 1), line.substring(indexFrom + 4, indexTo),
                    line.substring(indexTo + 2)));
        } else {
            throw new LinguoBotException("☹ OOPS!!! I'm sorry, but I don't know what that means. " +
                    "\nIf you wish to input a new task: \n" +
                    "Please include either 'todo', 'deadline' or 'event' in your input. " +
                    "\nOtherwise, if you wish to mark/unmark a task: \n" +
                    "Please include either 'mark' or 'unmark', followed by the task index in your input.");
        }
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        saveTaskListToFile(taskList);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        printLine();
    }

}
