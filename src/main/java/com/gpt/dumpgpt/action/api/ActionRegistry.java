package com.gpt.dumpgpt.action.api;

import com.gpt.dumpgpt.command.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActionRegistry {
    private static ActionRegistry actionRegistry;
    private final HashMap<String, Constructor<? extends Action>> ACTIONS = new HashMap<>();

    private ActionRegistry() {
    }

    public static ActionRegistry getRegistry() {
        if (actionRegistry == null) {
            actionRegistry = new ActionRegistry();
        }
        return actionRegistry;
    }

    private boolean isActionVerbValid(String actionVerb) {
        return !(actionVerb == null || actionVerb.isBlank());
    }

    private boolean isAliasesValid(String[] aliases) {
        return !(aliases == null || aliases.length == 0);
    }

    private ArrayList<String> getActionVerbs(Action action) {
        ArrayList<String> actionVerbs = new ArrayList<>();
        String actionVerb = action.getActionVerb();
        String[] aliases = action.getAliases();

        boolean isVerbValid = isActionVerbValid(actionVerb),
                isAliasesValid = isAliasesValid(aliases);

        if (isVerbValid) {
            actionVerbs.add(actionVerb);
        }

        if (isAliasesValid) {
            actionVerbs.addAll(List.of(aliases));
        }

        return actionVerbs;
    }

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

    public void execute(Command command) {
        String verb = command.getCommandVerb();
        Constructor<? extends Action> actionConstructor = ACTIONS.get(verb);
        if (actionConstructor == null) {
            return;
        }

        try {
            Action action = actionConstructor.newInstance(command);
            action.execute();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Unexpected error when executing command...");
        }
    }
}
