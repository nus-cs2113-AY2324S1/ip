package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ApplicationState;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;

import java.util.ArrayList;

public class Task {
    private final static String STATE_KEY = "Tasks";
    protected String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithStatus() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getNameWithStatus();
    }

    public void validate() throws DukeException {
        if (name == null || name.isBlank()) {
            throw new DukeException("Task name cannot be empty...");
        }
    }

    public void validate(String type) throws DukeException {
        if (name == null || name.isBlank()) {
            throw new DukeException(
                    String.format("%s name cannot be empty...", type)
            );
        }
    }


    public static void addTask(Task task) {
        ArrayList<Task> tasks = getTasks();
        tasks.add(task);
    }

    public static Task getTask(int pos) {
        ArrayList<Task> tasks = getTasks();
        if (pos < 0 || pos >= tasks.size()) {
            return null;
        }
        return tasks.get(pos);
    }

    public static Task getTask(Command command) {
        int taskNumber = ProgramConstants.parsePositiveNumber(command.getArguments());
        return getTask(--taskNumber);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getTasks() {
        ApplicationState appState = ApplicationState.getAppState();
        Object tasks = appState.getStateObject(STATE_KEY);
        if (tasks == null) {
            tasks = new ArrayList<Task>();
            appState.setStateObject(STATE_KEY, tasks);
        }
        return (ArrayList<Task>) tasks;
    }
}
