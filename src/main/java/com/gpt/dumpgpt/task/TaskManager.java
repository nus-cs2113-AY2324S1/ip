package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;

import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> TASKS = null;
    private static Task lastOperationTask = null;
    private static ArrayList<Task> lastOperationTasks = null;

    public TaskManager() {
        if (getTasks() == null) {
            TASKS = new ArrayList<>();
        }
    }

    public void addTask(Task task) {
        getTasks().add(task);
    }

    public boolean deleteTask(Task task) {
        boolean isRemoved = getTasks().remove(task);
        if (lastOperationTasks != null) {
            lastOperationTasks.remove(task);
        }
        return isRemoved;
    }

    public Task getTask(Command command) {
        int taskNumber = ProgramConstants.parsePositiveNumber(command.getArguments());
        if (lastOperationTasks != null) {
            return getTask(lastOperationTasks, --taskNumber);
        }
        return getTask(getTasks(), --taskNumber);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public void setLastOperation(Task task) {
        lastOperationTask = task;
    }

    public Task getLastOperationTask() {
        return lastOperationTask;
    }

    public void setLastOperation(ArrayList<Task> tasks) {
        lastOperationTasks = tasks;
    }

    public ArrayList<Task> getLastOperationTasks() {
        return lastOperationTasks;
    }

    /**
     * Restore tasks by setting {@link #TASKS} to provided ArrayList of tasks
     * <p>
     * Do nothing if {@link #TASKS} is not null
     *
     * @param tasks tasks to restore
     */
    public static void restoreTasks(ArrayList<Task> tasks) {
        if (TASKS == null) {
            TASKS = tasks;
        }
    }

    private Task getTask(ArrayList<Task> tasks, int pos) {
        if (pos < 0 || pos >= tasks.size()) {
            return null;
        }
        return tasks.get(pos);
    }
}
