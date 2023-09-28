package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Ui;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;

import java.util.ArrayList;

public class ListTask extends Action {
    private static final String ACTION_VERB = "list";
    public static final String NO_TASKS_PROMPT = "You have no tasks :D";

    public ListTask(Command command) {
        super(command, ACTION_VERB);
    }

    public ListTask(Command command, String actionVerb) {
        super(command, actionVerb);
    }

    protected void execute(Ui ui) {
        TaskManager taskManager = new TaskManager();
        ArrayList<Task> tasks = taskManager.getTasks();
        listTasks(ui, taskManager, tasks, NO_TASKS_PROMPT);
    }

    protected void listTasks(Ui ui, TaskManager taskManager, ArrayList<Task> tasks, String prompt) {
        taskManager.setLastOperation(tasks);
        if (tasks.isEmpty()) {
            ui.printWrapped(prompt);
            taskManager.setLastOperation(tasks);
            return;
        }

        printTasks(ui, tasks);
    }

    private void printTasks(Ui ui, ArrayList<Task> tasks) {
        int idx = 1;
        ui.printSeparator();
        for (Task task : tasks) {
            System.out.printf("%d. %s\n", idx++, task);
        }
        ui.printSeparator();
    }
}
