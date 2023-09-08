package com.gpt.dumpgpt;

import com.gpt.dumpgpt.action.api.ActionRegistry;
import com.gpt.dumpgpt.action.impl.*;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ApplicationState;
import com.gpt.dumpgpt.shared.ProgramConstants;

import java.util.Scanner;

public class Duke {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        ProgramConstants.greet();
        mainLoop();
    }

    public static void mainLoop() {
        ActionRegistry registry = registerActions();
        ApplicationState state = ApplicationState.getAppState();
        while (!state.getApplicationEnded()) {
            Command userCommand = getCommand();
            if (userCommand.isEmpty()) {
                ProgramConstants.printWrapped("Please provide an input!");
                continue;
            }
            registry.execute(userCommand);
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
