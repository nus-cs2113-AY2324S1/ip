package elvis.operation;

import elvis.task.Task;
import elvis.task.ToDo;
import elvis.task.Deadline;
import elvis.task.Event;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();   //Keeps track of all Task Instances made

    public static int getArraySize() {
        return tasks.size();
    }

    public static void addToDo(String description, int isDone) {
        tasks.add(new ToDo(description, isDone));
    }

    public static void addDeadline(String description, int isDone, String byWhen) {
        tasks.add(new Deadline(description, isDone, byWhen));
    }

    public static void addEvent(String description, int isDone, String startDateTime, String endDateTime) {
        tasks.add(new Event(description, isDone, startDateTime, endDateTime));
    }

    public static void taskRemover(int nthTask) {
        tasks.remove(nthTask);
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

    public static void setTaskStatus(int nthTask, boolean isDone) {
        tasks.get(nthTask).setStatus(isDone);
    }

    public static String getTaskDescription(int nthTask) {
        return tasks.get(nthTask).getDescription();
    }

    public static void saver() {
        Storage.saver(tasks);
    }

    public static boolean isArrayEmpty() {
        return tasks.isEmpty();
    }
}
