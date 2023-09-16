package com.gpt.dumpgpt;

import com.gpt.dumpgpt.action.api.ActionRegistry;
import com.gpt.dumpgpt.action.impl.*;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ApplicationState;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Task;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        restoreTasksSafely();
        ProgramConstants.greet();
        mainLoop();
    }

    private static void restoreTasksSafely() {
        try {
            Task.restoreTasks();
        } catch (Exception e) {
            ProgramConstants.printWrapped("Failed to restore old tasks...");
        }
    }

    private static void saveTasksSafely() {
        try {
            Task.saveTasks();
        } catch (IOException e) {
            ProgramConstants.printWrapped("Failed to save tasks...");
        } catch (DukeException e) {
            ProgramConstants.printWrapped(e.toString());
        }
    }

    public static void mainLoop() {
        ActionRegistry registry = registerActions();
        ApplicationState state = ApplicationState.getAppState();
        while (!state.getApplicationEnded()) {
            Command userCommand = getCommand();
            registry.execute(userCommand);
            saveTasksSafely();
        }
    }

    public static ActionRegistry registerActions() {
        ActionRegistry registry = ActionRegistry.getRegistry();
        registry.registerAction(new AddTask(null));
        registry.registerAction(new ListTask(null));
        registry.registerAction(new MarkTask(null));
        registry.registerAction(new UnmarkTask(null));
        registry.registerAction(new EndProgram(null));
        return registry;
    }

    public static Command getCommand() {
        System.out.print("User: ");
        String userInput = SCANNER.nextLine();
        Command userCommand = new Command(userInput);
        userCommand.parse();
        return userCommand;
    }
}
