package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;

import java.util.ArrayList;

public class ListTask extends Action {
    private static final String ACTION_VERB = "list";

    public ListTask(Command command) {
        super(command, ACTION_VERB);
    }

    protected void execute() {
        TaskManager taskManager = new TaskManager();
        ArrayList<Task> tasks = taskManager.getTasks();
        if (tasks.isEmpty()) {
            ProgramConstants.printWrapped("You have no tasks :D");
            return;
        }

        printTasks(tasks);
    }

    private void printTasks(ArrayList<Task> tasks) {
        int idx = 1;
        ProgramConstants.printSeparator();
        for (Task task : tasks) {
            System.out.printf("%d. %s\n", idx++, task);
        }
        ProgramConstants.printSeparator();
    }
}
