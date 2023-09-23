package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.Ui;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindTask extends ListTask {
    private static final String ACTION_VERB = "find";
    public static final String NO_TASKS_PROMPT = "No tasks with matching name :(";

    public FindTask(Command command) {
        super(command, ACTION_VERB);
    }

    protected void execute(Ui ui) {
        TaskManager taskManager = new TaskManager();
        ArrayList<Task> tasks = getFilteredTasks(taskManager);
        listTasks(ui, taskManager, tasks, NO_TASKS_PROMPT);
    }

    private ArrayList<Task> getFilteredTasks(TaskManager taskManager) {
        Command command = getCommand();
        return (ArrayList<Task>) taskManager.getTasks()
                .stream()
                .filter(t -> t.getName().contains(command.getArguments()))
                .collect(Collectors.toList());
    }
}
