package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;

import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> TASKS = null;

    public TaskManager() {
        if (TASKS == null) {
            TASKS = new ArrayList<>();
        }
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public boolean deleteTask(Task task) {
        return TASKS.remove(task);
    }

    public Task getTask(int pos) {
        if (pos < 0 || pos >= TASKS.size()) {
            return null;
        }
        return TASKS.get(pos);
    }

    public Task getTask(Command command) {
        int taskNumber = ProgramConstants.parsePositiveNumber(command.getArguments());
        return getTask(--taskNumber);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
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

}
