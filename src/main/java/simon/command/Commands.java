package simon.command;

import simon.exception.*;
import simon.task.*;
import static simon.UI.Printer.*;
import static simon.constant.TaskConstants.*;

public class Commands {
    public static void markTask(String taskNumber, Task[] tasks) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            tasks[target].markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [X] " + tasks[target].getDescription());
        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tSorry! The tasks number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-" + MAX_TASKS);
        }
        System.out.println("\t" + line);
    }

    public static void unmarkTask(String taskNumber, Task[] tasks) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            tasks[target].unmarkAsDone();
            System.out.println("\tOkay, I've marked this task as not done yet:");
            System.out.println("\t  [] " + tasks[target].getDescription());
        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tSorry! The tasks number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-" + MAX_TASKS);
        }
        System.out.println("\t" + line);
    }

    public static void addTodo(String description, Task[] tasks) throws SimonException {
        if (Task.getNumberOfTask() == MAX_TASKS) {
            throw new SimonException();
        }
        tasks[Task.getNumberOfTask()] = new Todo(description);

        System.out.println("\t" + line);
        printAddTaskMessage(tasks);
        printNumberOfTasks(tasks);
        System.out.println("\t" + line);
    }

    public static void addEvent(String event, Task[] tasks) throws SimonException {
        if (Task.getNumberOfTask() == MAX_TASKS) {
            throw new SimonException();
        }
        System.out.println("\t" + line);
        try {
            //Split between 'description' and '/from and /to'
            String[] splitEvent = event.split(" /from ", 2);
            String description = splitEvent[0];
            //Split between '/from' and '/to'
            String[] time = splitEvent[1].split(" /to ", 2);
            String from = time[0];
            String to = time[1];
            tasks[Task.getNumberOfTask()] = new Event(description, from, to);

            printAddTaskMessage(tasks);
            printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the time of your event in the following format:");
            System.out.println("\tevent [description] /from [start time] /to [end time]");
        }
        System.out.println("\t" + line);
    }

    public static void addDeadline(String deadline, Task[] tasks) throws SimonException {
        if (Task.getNumberOfTask() == MAX_TASKS) {
            throw new SimonException();
        }
        System.out.println("\t" + line);
        try {
            //Split between 'description' and '/by'
            String[] splitDeadline = deadline.split(" /by ", 2);
            String description = splitDeadline[0];
            String by = splitDeadline[1];
            tasks[Task.getNumberOfTask()] = new Deadline(description, by);


            printAddTaskMessage(tasks);
            printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the deadline of your task is in the following format:");
            System.out.println("\tdeadline [description] /by [deadline]");
        }
        System.out.println("\t" + line);
    }

    /**
     * public static void addTask(String userInput, duke.task.Task[] tasks) {
     *         tasks[duke.task.Task.getNumberOfTask()] = new duke.task.Task(userInput);
     *         System.out.println("\t" + line);
     *         System.out.println("\t" + "added: " + userInput);
     *         System.out.println("\t" + line);
     *         }
     */
}
