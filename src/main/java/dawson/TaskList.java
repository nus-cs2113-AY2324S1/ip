package dawson;

import java.util.List;

import dawson.task.Task;

import java.util.ArrayList;

public class TaskList {
    private Task[] taskList;
    private int size;

    public TaskList() {
        this.taskList = new Task[100];
        this.size = 0;
    }

    public void add(Task task) {
        taskList[size] = task;
        size++;

        String[] addText = {
                "Got it. I've added this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", size)
        };
        Dawson.printText(addText);
    }

    public boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }

    public String markAsDoneIndex(int index) {
        if (!isIndexValid(index)) {
            return "";
        }
        taskList[index].markAsDone();
        return taskList[index].toString();
    }

    public String unmarkIndex(int index) {
        if (!isIndexValid(index)) {
            return "";
        }
        taskList[index].unmark();
        return taskList[index].toString();
    }

    public String[] getTaskStrings() {
        if (size == 0) {
            return new String[] { "Empty list!" };
        }

        List<String> result = new ArrayList<String>();
        result.add("Here are the tasks in your list: ");

        for (int i = 0; i < size; i++) {
            String line = String.format("%d. %s", i + 1, taskList[i]);
            result.add(line);
        }

        String[] resultStrings = new String[result.size()];
        return result.toArray(resultStrings);
    }
}
