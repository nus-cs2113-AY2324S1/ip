package CSGPT;

import Exceptions.CSGPTMissingTaskException;

public class TaskList {
    private final Task[] list;
    private int size;

    public TaskList() {
        list = new Task[100];
        size = 0;
    }

    public void add(Task task) {
        list[size] = task;
        size++;
    }

    public Task getTask(int taskNumber) {
        return list[taskNumber - 1];
    }

    public void markAsDone(int taskNumber) throws CSGPTMissingTaskException {
        if (taskNumber > size || taskNumber < 1)
            throw new CSGPTMissingTaskException();
        list[taskNumber - 1].markAsDone();
    }

    public int size() {
        return size;
    }

    public void getTasks() {
        if (size == 0) {
            CSGPT.printText("You have no tasks at hand, mortal.");
            return;
        }
        String[] text = new String[size + 1];
        text[0] = "These are the chores you have at hand, mortal:";
        for (int i = 0; i < size; i++) {
            text[i+1] = ((i + 1) + ". " + list[i].toString());
        }
        CSGPT.printMultipleText(text);
    }
}
