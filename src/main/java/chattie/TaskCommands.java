package chattie;

import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.tasks.Event;
import chattie.tasks.Task;
import chattie.tasks.Todo;

import java.util.ArrayList;

public class TaskCommands {


    private static void printAddTask (ArrayList<Task> list, int count) {
        int index = count - 1;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list.get(index));
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    public static void listTasks(ArrayList<Task> list, int count) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\t" + (i+1) + ". ");
            System.out.println(list.get(i));
        }
    }

    public static int addTodo(ArrayList<Task> list, String line, int count) throws ChattieException {
        if (line.trim().length() < 5) {
            throw new ChattieException(ErrorType.TODO);
        }
        String todo = line.substring(5);
        list.add(new Todo(todo));
        count++;
        printAddTask(list, count);
        return count;
    }

    public static int addDeadline(ArrayList<Task> list, String line, int count) throws ChattieException {
        if (line.trim().length() < 9) { //check if deadline is empty
            throw new ChattieException(ErrorType.EMPTY_DEADLINE);
        }

        int slashIndex = line.indexOf("/by");
        if (slashIndex < 0) {
            throw new ChattieException(ErrorType.INVALID_DEADLINE); //check if '/by' is present
        }

        String task = line.substring(9, slashIndex);
        String by = line.substring(slashIndex + 3);
        if (task.isEmpty() || by.isEmpty()) {
            throw new ChattieException(ErrorType.INVALID_DEADLINE); //check if [task] or [by] empty
        }

        list.add(new Deadline(task, by));
        count++;
        printAddTask(list, count);
        return count;
    }

    public static int addEvent(ArrayList<Task> list, String line, int count) throws ChattieException{
        if (line.trim().length() < 6) {
            throw new ChattieException(ErrorType.EMPTY_EVENT); //check if event is empty
        }

        int firstSlash = line.indexOf("/from");
        int secondSlash = line.indexOf("/to");
        if (firstSlash < 0 || secondSlash < 0) {
            throw new ChattieException(ErrorType.INVALID_EVENT); //check if '/from' or '/to' present
        }

        String task = line.substring(6, firstSlash);
        String from = line.substring(firstSlash + 5, secondSlash);
        String to = line.substring(secondSlash + 3);
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ChattieException(ErrorType.INVALID_EVENT); //check if [task], [from], or [to] is empty
        }

        list.add(new Event(task, from, to));
        count++;
        printAddTask(list, count);
        return count;
    }

    public static void markTask(ArrayList<Task> list, String[] command) throws ChattieException {
        int taskNum = Integer.parseInt(command[1]) - 1;
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }
        if(command[0].equalsIgnoreCase("mark")) {
            list.get(taskNum).setDone(true);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + list.get(taskNum));
        } else {
            list.get(taskNum).setDone(false);
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + list.get(taskNum));
        }
    }

    public static void deleteTask(ArrayList<Task> list, String[] command) throws ChattieException {
        int taskNum = Integer.parseInt(command[1]) - 1;
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + list.get(taskNum));
        list.remove(taskNum);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }
}
