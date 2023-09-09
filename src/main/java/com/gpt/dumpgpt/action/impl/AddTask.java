package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Deadline;
import com.gpt.dumpgpt.task.Event;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.Todo;

public class AddTask extends Action {
    private static final String ACTION_VERB = "";
    private static final String[] ALIASES = new String[]{
            "deadline", "event", "todo"
    };

    public AddTask(Command command) {
        super(command, ACTION_VERB);
    }

    @Override
    protected String[] getAliases() {
        return ALIASES;
    }

    protected void execute() throws DukeException {
        Task task = createNewTask();
        throwIfInvalidTask(task);
        Task.addTask(task);
        printSuccess(task);
    }

    private static void throwIfInvalidTask(Task task) throws DukeException {
        if (task == null) {
            throw new DukeException("Failed to add new task...");
        }
        task.validate();
    }

    private Task createNewTask() {
        Command command = getCommand();
        switch (command.getCommandVerb()) {
        case "deadline":
            return new Deadline(command.getArguments(), command.getOptions("by"));
        case "event":
            return new Event(command.getArguments(), command.getOptions("from"), command.getOptions("to"));
        case "todo":
            return new Todo(command.getArguments());
        }
        return null;
    }

    private void printSuccess(Task task) {
        int tasksCount = Task.getTasks().size();
        String taskSummary = String.format(
                "You now have %d %s in the list!",
                tasksCount,
                (tasksCount > 1) ? "tasks" : "task"
        );
        ProgramConstants.printWrapped(new String[]{
                "Great! I've added the following task:",
                "\t" + task.toString(),
                taskSummary
        });
    }
}
