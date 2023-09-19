package Ken;

import Exceptions.KenMissingTaskException;

import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int taskNumber) throws KenMissingTaskException {
        try {
            return list.get(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new KenMissingTaskException();
        }
    }

    public int getSize() {
        return list.size();
    }

    public void updateStatus(int taskNumber, boolean status) throws KenMissingTaskException {
        try {
            list.get(taskNumber).setDone(status);
        } catch (IndexOutOfBoundsException e) {
            throw new KenMissingTaskException();
        }
    }

    public void getTasks() {
        int taskSize = list.size();
        String[] texts = new String[taskSize + 1];
        texts[0] = "Behold, your list of enchanting tasks!";
        for (int i = 1; i <= taskSize; i++) {
            Task currentTask = list.get(i - 1);
            texts[i] = "\t" + i + "." + currentTask.toString();
        }
        Ui.printTexts(texts);
    }

    public void deleteTask(int taskNumber) throws KenMissingTaskException{
        try {
            list.remove(taskNumber);
        } catch(IndexOutOfBoundsException e) {
            throw new KenMissingTaskException();
        }
    }
}
