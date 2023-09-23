package com.gpt.dumpgpt;

import com.gpt.dumpgpt.action.api.ActionRegistry;
import com.gpt.dumpgpt.action.impl.AddTask;
import com.gpt.dumpgpt.action.impl.DeleteTask;
import com.gpt.dumpgpt.action.impl.EndProgram;
import com.gpt.dumpgpt.action.impl.ListTask;
import com.gpt.dumpgpt.action.impl.MarkTask;
import com.gpt.dumpgpt.action.impl.UnmarkTask;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.command.Parser;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Storage;
import com.gpt.dumpgpt.shared.Ui;
import com.gpt.dumpgpt.task.TaskManager;

import java.io.IOException;

public class Duke {
    private final String TASKS_FILE;
    private final Ui UI;

    public static void main(String[] args) {
        (new Duke("data/tasks.bin")).run();
    }

    public Duke(String tasksFile) {
        TASKS_FILE = tasksFile;
        UI = new Ui();
    }

    public void run() {
        restoreTasksSafely();
        UI.greet();
        mainLoop();
    }

    private void restoreTasksSafely() {
        try {
            Storage storage = new Storage(TASKS_FILE);
            TaskManager.restoreTasks(storage.restoreTasks());
        } catch (Exception e) {
            UI.printWrapped("Failed to restore old tasks...");
        }
    }

    private void saveTasksSafely() {
        try {
            TaskManager taskManager = new TaskManager();
            Storage storage = new Storage(TASKS_FILE);
            storage.saveTasks(taskManager.getTasks());
        } catch (IOException e) {
            UI.printWrapped("Failed to save tasks...");
        } catch (DukeException e) {
            UI.printWrapped(e.toString());
        }
    }

    public void mainLoop() {
        ActionRegistry registry = registerActions();
        while (!ProgramConstants.getIsEnded()) {
            String userInput = UI.getInput();
            Command userCommand = Parser.parse(userInput);
            registry.execute(userCommand, UI);
            saveTasksSafely();
        }
    }

    public ActionRegistry registerActions() {
        ActionRegistry registry = ActionRegistry.getRegistry();
        registry.registerAction(new AddTask(null));
        registry.registerAction(new DeleteTask(null));
        registry.registerAction(new ListTask(null));
        registry.registerAction(new MarkTask(null));
        registry.registerAction(new UnmarkTask(null));
        registry.registerAction(new EndProgram(null));
        return registry;
    }

}
