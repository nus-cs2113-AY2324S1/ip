package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Task;

import java.util.ArrayList;

public class ListTask extends Action {
    private static final String ACTION_VERB = "list";
    public ListTask(Command command) {
        super(command, ACTION_VERB);
    }

    protected void execute() {
        ArrayList<Task> tasks = Task.getTasks();
        int idx = 1;
        ProgramConstants.printSeparator();
        for (Task task : tasks) {
            System.out.printf("%d. %s\n", idx++, task);
        }
        ProgramConstants.printSeparator();
    }
}
