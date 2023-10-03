package dawson.task;

import dawson.exception.DawsonException;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task markAsDoneIndex(int index) throws DawsonException {
        try {
            Task task = taskList.get(index);
            task.markAsDone();
            return task;

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    public Task unmarkIndex(int index) throws DawsonException {
        try {
            Task task = taskList.get(index);
            task.unmark();
            return task;

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    public Task deleteTask(int index) throws DawsonException {
        try {
            Task removedTask = taskList.remove(index);
            return removedTask;

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

    public ArrayList<String> findTasksWithDate(LocalDate queryDate) {
        ArrayList<String> result = new ArrayList<String>();
        if (queryDate == null) {
            return result;
        }

        int counter = 1;
        for (Task task : taskList) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.containsQueryDate(queryDate)) {
                    String line = String.format("%d. %s", counter, task);
                    result.add(line);
                    counter++;
                }

            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.containsQueryDate(queryDate)) {
                    String line = String.format("%d. %s", counter, task);
                    result.add(line);
                    counter++;
                }
            }
        }

        return result;
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
