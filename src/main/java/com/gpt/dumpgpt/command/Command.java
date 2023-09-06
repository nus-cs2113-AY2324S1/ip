package com.gpt.dumpgpt.command;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
    public static final String DEFAULT_ARGS = "_ARGS";
    private String originalCommand;
    private String commandWord;
    private String[] tokens;
    private final HashMap<String, String> OPTIONS = new HashMap<>();
    private boolean isEmpty = false;

    public Command(String originalCommand) {
        this.originalCommand = originalCommand;
        isEmpty = originalCommand.isBlank();
    }

    public String getOriginalCommand() {
        return originalCommand;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getArguments() {
        return getOptions(DEFAULT_ARGS);
    }

    public String getOptions(String key) {
        return OPTIONS.get(key.strip());
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void parse() {
        tokens = originalCommand.split(" ");
        if (tokens.length > 0) {
            commandWord = tokens[0].strip().toLowerCase();
        }
        parseTokens();
    }

    public void parseTokens() {
        if (isEmpty) {
            return;
        }

        String optionKey = DEFAULT_ARGS;
        ArrayList<String> currentSet = new ArrayList<>();
        for (int i = 1; i < tokens.length; ++i) {
            String token = tokens[i];
            if (token.startsWith("/")) {
                addOption(optionKey, String.join(" ", currentSet));
                optionKey = token.substring(1);
                currentSet.clear();
            } else {
                currentSet.add(token);
            }
        }

        if (!currentSet.isEmpty()) {
            addOption(optionKey, String.join(" ", currentSet));
        }
    }

    private void addOption(String key, String value) {
        if (key.isBlank() || value.isBlank()) {
            return;
        }
        OPTIONS.put(key.strip(), value.strip());
    }
}
