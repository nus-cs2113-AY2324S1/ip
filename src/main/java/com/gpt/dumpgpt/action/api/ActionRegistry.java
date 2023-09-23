package com.gpt.dumpgpt.action.api;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.Ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActionRegistry {
    public static final String UNKNOWN_COMMAND = "Unknown command...";
    public static final String UNEXPECTED_ERROR = "Unexpected error when executing command...";
    public static final String MISSING_INPUT = "Please provide an input!";
    private static ActionRegistry actionRegistry;
    private final HashMap<String, Constructor<? extends Action>> ACTIONS = new HashMap<>();

    private ActionRegistry() {
    }

    /**
     * Gets singleton instance of registry
     *
     * @return ActionRegistry instance
     */
    public static ActionRegistry getRegistry() {
        if (actionRegistry == null) {
            actionRegistry = new ActionRegistry();
        }
        return actionRegistry;
    }

    private boolean checkActionVerbValid(String actionVerb) {
        return !(actionVerb == null || actionVerb.isBlank());
    }

    private boolean checkAliasesValid(String[] aliases) {
        return !(aliases == null || aliases.length == 0);
    }

    /**
     * Returns a list of valid action verbs
     * for an Action object
     *
     * @param action Action object to extract verbs for
     * @return ArrayList of valid action verbs for action
     */
    private ArrayList<String> getActionVerbs(Action action) {
        ArrayList<String> actionVerbs = new ArrayList<>();
        String actionVerb = action.getActionVerb();
        String[] aliases = action.getAliases();

        boolean isVerbValid = checkActionVerbValid(actionVerb),
                isAliasesValid = checkAliasesValid(aliases);

        if (isVerbValid) {
            actionVerbs.add(actionVerb.strip());
        }

        if (isAliasesValid) {
            actionVerbs.addAll(
                    Stream.of(aliases)
                            .parallel()
                            .filter(Predicate.not(String::isBlank))
                            .map(String::strip)
                            .collect(Collectors.toList())
            );
        }

        return actionVerbs;
    }

    /**
     * Registers a given action with the registry
     *
     * @param action action to be registered
     */
    public void registerAction(Action action) {
        ArrayList<String> actionVerbs = getActionVerbs(action);
        for (String actionVerb : actionVerbs) {
            registerAction(actionVerb, action);
        }
    }

    private void registerAction(String actionVerb, Action action) {
        try {
            ACTIONS.put(actionVerb, action.getClass().getDeclaredConstructor(Command.class));
        } catch (NoSuchMethodException e) {
            // do nothing
        }
    }

    /**
     * Executes a command
     * <p>
     * Finds the matching action registered in registry based on verb
     * parsed in command object.
     * <p>
     * If match found, constructions action object with
     * command object as parameter and executes the action.
     *
     * @param command the command to be executed
     */
    public void execute(Command command, Ui ui) {
        if (command == null || command.isEmpty()) {
            ui.printWrapped(MISSING_INPUT);
            return;
        }

        String verb = command.getCommandVerb();
        Constructor<? extends Action> actionConstructor = ACTIONS.get(verb);
        if (actionConstructor == null) {
            ui.printWrapped(UNKNOWN_COMMAND);
            return;
        }

        try {
            Action action = actionConstructor.newInstance(command);
            action.execute(ui);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            ui.printWrapped(UNEXPECTED_ERROR);
        } catch (DukeException e) {
            ui.printWrapped(e.toString());
        }
    }
}
