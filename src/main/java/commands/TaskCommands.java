package commands;

import exception.*;
import task.*;
import static UI.Printer.*;
import java.util.ArrayList;
import static commands.DataCommands.*;
import static data.FilePath.SimonFilePath.simontxtFilePath;

public class TaskCommands {
    public static void markTask(String taskNumber, ArrayList<Task> tasks) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            tasks.get(target).markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [X] " + tasks.get(target).getDescription());
            String newText = tasks.get(target).toText();
            editTextFile(simontxtFilePath, newText, target + 1);
        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tSorry! The task number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-" + Task.getNumberOfTask());
        }
        System.out.println("\t" + line);
    }

    public static void unmarkTask(String taskNumber, ArrayList<Task> tasks) {
        //Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            tasks.get(target).unmarkAsDone();
            System.out.println("\tOkay, I've marked this task as not done yet:");
            System.out.println("\t  [] " + tasks.get(target).getDescription());
            String newText = tasks.get(target).toText();
            editTextFile(simontxtFilePath, newText, target + 1);
        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tSorry! The task number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-" + Task.getNumberOfTask());
        }
        System.out.println("\t" + line);
    }

    public static void deleteTask(String taskNumber, ArrayList<Task> tasks){
        int target = Integer.parseInt(taskNumber) - 1;
        System.out.println("\t" + line);
        try {
            String task = tasks.get(target).toString();
            tasks.remove(target);
            Task.deleteOneTask();
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t  " + task);
            printNumberOfTasks(tasks);

            for (int i = target; i < Task.getNumberOfTask(); i++) {
                String newText = tasks.get(i).toText();
                editTextFile(simontxtFilePath, newText, i + 1);
            }
            deleteLineFromFile(simontxtFilePath, Task.getNumberOfTask());

        } catch (NullPointerException e) {
            System.out.println("\tSorry! There is no task associated with this number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tSorry! The task number inputted is out of bounds");
            System.out.println("\tPlease key in a number from 1-" + Task.getNumberOfTask());
        }
        System.out.println("\t" + line);
    }

    public static void addTodo(String description, ArrayList<Task> tasks) throws SimonException {
        tasks.add(new Todo(description));

        addTextToFile(simontxtFilePath, tasks.get(Task.getNumberOfTask() - 1).toText());

        System.out.println("\t" + line);
        printAddTaskMessage(tasks);
        printNumberOfTasks(tasks);
        System.out.println("\t" + line);
    }

    public static void addEvent(String event, ArrayList<Task> tasks) throws SimonException {
        System.out.println("\t" + line);
        try {
            //Split between 'description' and '/from and /to'
            String[] splitEvent = event.split(" /from ", 2);
            String description = splitEvent[0];
            //Split between '/from' and '/to'
            String[] time = splitEvent[1].split(" /to ", 2);
            String from = time[0];
            String to = time[1];
            tasks.add(new Event(description, from, to));
            addTextToFile(simontxtFilePath, tasks.get(Task.getNumberOfTask() - 1).toText());

            printAddTaskMessage(tasks);
            printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the time of your event in the following format:");
            System.out.println("\tevent [description] /from [start time] /to [end time]");
        }
        System.out.println("\t" + line);
    }

    public static void addDeadline(String deadline, ArrayList<Task> tasks) throws SimonException {
        System.out.println("\t" + line);
        try {
            //Split between 'description' and '/by'
            String[] splitDeadline = deadline.split(" /by ", 2);
            String description = splitDeadline[0];
            String by = splitDeadline[1];
            tasks.add(new Deadline(description, by));
            addTextToFile(simontxtFilePath, tasks.get(Task.getNumberOfTask() - 1).toText());

            printAddTaskMessage(tasks);
            printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease include when the deadline of your task is in the following format:");
            System.out.println("\tdeadline [description] /by [deadline]");
        }
        System.out.println("\t" + line);
    }
}
