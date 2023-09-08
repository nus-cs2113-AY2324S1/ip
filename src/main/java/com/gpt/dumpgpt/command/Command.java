package com.gpt.dumpgpt.command;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
    private static final String DEFAULT_ARGS = "_ARGS";
    private final String ORIGINAL_INPUT;
    private String commandVerb;
    private String[] tokens;
    private final HashMap<String, String> OPTIONS = new HashMap<>();
    private boolean isEmpty = true;

    public Command(String originalInput) {
        this.ORIGINAL_INPUT = originalInput;
        isEmpty = originalInput.isBlank();
    }

    public String getOriginalInput() {
        return ORIGINAL_INPUT;
    }

    public String getCommandVerb() {
        return commandVerb;
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
        tokens = ORIGINAL_INPUT.split(" ");
        if (tokens.length > 0) {
            commandVerb = tokens[0].strip().toLowerCase();
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
