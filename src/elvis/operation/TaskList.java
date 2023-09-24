package elvis.operation;

import elvis.exception.UnknownInputException;
import elvis.task.Deadline;
import elvis.task.Event;
import elvis.task.Task;
import elvis.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();   //Keeps track of all Task Instances made

    public static int getArraySize() {
        return tasks.size();
    }

    public static Task getTask(int nthTask) {
        return tasks.get(nthTask);
    }

    public static char getTaskType(int nthTask) {
        return tasks.get(nthTask).getTaskType();
    }

    public static String getTaskStatus(int nthTask) {
        return tasks.get(nthTask).getStatus();
    }

    public static String getTaskDescription(int nthTask) {
        return tasks.get(nthTask).getDescription();
    }

    public static void saveRequester() {
        Storage.saver(tasks);
    }

    public static boolean isArrayEmpty() {
        return tasks.isEmpty();
    }

    public static void listOut(boolean isFromFile) {
        if (!isFromFile) {
            Ui.listMessagePrinter();
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            Ui.print(i);
        }
        Ui.taskCountPrinter();
    }

    public static void taskMarker(int numberInput) {
        int nthTask = numberInput - 1;
        if (nthTask > tasks.size()-1 || nthTask < 0 || tasks.isEmpty()) {
            Ui.noSuchTaskMessagePrinter();
            return;
        }
        tasks.get(nthTask).setStatus(true);
        Ui.taskMarkedMessagePrinter();
        Ui.print(nthTask);
    }

    public static void taskUnmarker(int numberInput) {
        int nthTask = numberInput - 1;  //Remember that index of list starts from 0
        if (nthTask > tasks.size()-1 || nthTask < 0 || tasks.isEmpty()) {
            Ui.noSuchTaskMessagePrinter();
            return;
        }
        tasks.get(nthTask).setStatus(false);
        Ui.taskUnmarkedMessagePrinter();
        Ui.print(nthTask);
    }

    public static void insertToDo(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String description = inputBuffer.replace("todo ", "");
        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            tasks.add(new ToDo(description, isDone));
        } else {
            tasks.add(new ToDo(description, 0));
            Ui.taskAddedMessagePrinter();
        }
    }

    public static void insertDeadline(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String date = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim();
        String description = inputBuffer.replace("deadline ", "");  //Get rid of "deadline "
        description = description.substring(0, description.indexOf("/by"));          //Get rid of "/by..."
        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            tasks.add(new Deadline(description, isDone, date));
        } else {
            tasks.add(new Deadline(description, 0, date));
            Ui.taskAddedMessagePrinter();
        }
    }

    public static void insertEvent(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String startTime = inputBuffer.substring(inputBuffer.indexOf("/from") + 5, inputBuffer.indexOf("/to")).trim();
        String endTime = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
        String description = inputBuffer.replace("event ", "");         //Get rid of "event "
        description = description.substring(0, description.indexOf("/from")).trim();    //Get rid of "/from..."

        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            tasks.add(new Event(description, isDone,  startTime, endTime));
        } else {
            tasks.add(new Event(description, 0, startTime, endTime));
            Ui.taskAddedMessagePrinter();
        }
    }

    public static void taskRemover(int numberInput) {
        int nthTask = numberInput - 1;
        if (nthTask > tasks.size()-1 || nthTask < 0 || tasks.isEmpty()) {
            Ui.noSuchTaskMessagePrinter();
            return;
        }
        Ui.taskRemovedMessagePrinter();
        Ui.print(nthTask);
        tasks.remove(nthTask);
        Ui.taskCountPrinter();
    }

    public static int getStatusFromFile(String description) throws UnknownInputException {
        Scanner lineReader = new Scanner(description);  //Used to read mark/unmark from file
        if (!lineReader.hasNextInt()) {
            throw new UnknownInputException();
        }
        int isDone = lineReader.nextInt();
        if (isDone < 0 || isDone > 1) {
            throw new UnknownInputException();
        }
        return isDone;
    }
}
