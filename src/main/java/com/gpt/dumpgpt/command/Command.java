package com.gpt.dumpgpt.command;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
    private static final String DEFAULT_ARGS = "_ARGS";
    public static final String OPTION_PREFIX = "/";
    public static final String TOKEN_DELIM = " ";
    private final String ORIGINAL_INPUT;
    private final HashMap<String, String> OPTIONS = new HashMap<>();
    private final boolean IS_EMPTY;
    private String commandVerb;
    private String[] tokens;

    public Command(String originalInput) {
        this.ORIGINAL_INPUT = originalInput;
        IS_EMPTY = originalInput.isBlank();
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
        return IS_EMPTY;
    }

    /**
     * Parse the tokens in the original input
     * to extract verb, arguments and options
     */
    public void parse() {
        tokens = ORIGINAL_INPUT.split(TOKEN_DELIM);
        if (tokens.length > 0) {
            commandVerb = tokens[0].strip().toLowerCase();
        }
        parseTokens();
    }

    /**
     * Iterates through the list of extracted tokens and
     * populates the OPTIONS hashmap accordingly.
     * <p>
     * Arguments will be store with {@link #DEFAULT_ARGS default argument} as key
     * <p>
     * Options will be store with key as in command syntax.
     * ("{@link #OPTION_PREFIX {prefix}}myOption" will be store with "myOption" as key)
     */
    private void parseTokens() {
        if (IS_EMPTY) {
            return;
        }

        String optionKey = DEFAULT_ARGS;
        ArrayList<String> currentTokens = new ArrayList<>();
        for (int i = 1; i < tokens.length; ++i) {
            String token = tokens[i];
            if (token.startsWith(OPTION_PREFIX)) {
                addOption(optionKey, currentTokens);
                optionKey = token.substring(1);
                currentTokens.clear();
                continue;
            }
            currentTokens.add(token);
        }

        addOption(optionKey, currentTokens);
    }

    private void addOption(String key, ArrayList<String> tokens) {
        String value = String.join(" ", tokens);
        if (key.isBlank() || value.isBlank()) {
            return;
        }
        OPTIONS.put(key.strip(), value.strip());
    }
}
