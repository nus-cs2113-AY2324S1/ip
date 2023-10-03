package chattie;

import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    private static ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public static int getSize() {
        return list.size();
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public static Task getTask(int index) {
        return list.get(index);
    }

    public static void add(Task task) {
        list.add(task);
    }

    public static void delete(int taskNum) throws ChattieException {
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }
        list.remove(taskNum);
    }

    public static void mark(int taskNum) throws ChattieException {
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }

        list.get(taskNum).setDone(true);
    }

    public static void unmark(int taskNum) throws ChattieException {
        if (taskNum < 0 || taskNum >= list.size()) {
            throw new ChattieException(ErrorType.OUT_OF_BOUNDS);
        }

        list.get(taskNum).setDone(false);
    }

    public static ArrayList<Task> find (String item) {
        return (ArrayList<Task>) list.stream()
                .filter(task -> task.getTask().contains(item))
                .collect(Collectors.toList());
    }
}
