package dawson.task;

import dawson.Dawson;
import dawson.exception.DawsonException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);

        String[] addText = {
                "Got it. I've added this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size())
        };
        Dawson.printText(addText);
    }

    public void markAsDoneIndex(int index) throws DawsonException {
        try {
            Task task = taskList.get(index);
            task.markAsDone();

            String[] markAsDoneTexts = {
                    "Nice! I've marked this task as done: ",
                    "  " + task.toString()
            };
            Dawson.printText(markAsDoneTexts);

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    public void unmarkIndex(int index) throws DawsonException {
        try {
            Task task = taskList.get(index);
            task.unmark();

            String[] unmarkIndexTexts = {
                    "Ok, I've marked this task as not done yet: ",
                    "  " + task.toString()
            };
            Dawson.printText(unmarkIndexTexts);

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    public void deleteTask(int index) throws DawsonException {
        try {
            Task removedTask = taskList.remove(index);
            
            String[] deleteText = {
                    "Noted. I've removed this task:",
                    "  " + removedTask.toString(),
                    String.format("Now you have %d tasks in the list.", taskList.size())
            };
            Dawson.printText(deleteText);

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    public String[] getTaskStrings() {
        if (taskList.size() == 0) {
            return new String[] { "Empty list!" };
        }

        ArrayList<String> result = new ArrayList<String>();
        result.add("Here are the tasks in your list: ");

        for (int i = 0; i < taskList.size(); i++) {
            String line = String.format("%d. %s", i + 1, taskList.get(i));
            result.add(line);
        }

        String[] resultStrings = new String[result.size()];
        return result.toArray(resultStrings);
    }

    public String encodeTaskList() {
        boolean firstLine = true;
        StringBuilder result = new StringBuilder();

        for (Task task : taskList) {
            String taskString = task.encode();
            if (!firstLine) { // Only add next line after first line
                result.append(System.lineSeparator());
            }
            result.append(taskString);
            firstLine = false;
        }

        return result.toString();
    }
}
