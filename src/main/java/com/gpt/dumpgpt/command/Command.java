package com.gpt.dumpgpt.command;

import java.util.HashMap;

public class Command {
    private static final String DEFAULT_ARGS = "_ARGS";
    private String originalInput = "";
    private final HashMap<String, String> OPTIONS = new HashMap<>();
    private boolean isEmpty = true;
    private String commandVerb = "";

    public String getOriginalInput() {
        return originalInput;
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

    protected void setOriginalInput(String originalInput) {
        this.originalInput = originalInput;
        this.isEmpty = originalInput.isBlank();
    }

    protected void setCommandVerb(String commandVerb) {
        this.commandVerb = commandVerb;
    }

    /**
     * Adds argument or options to command object
     *
     * @param key   key used to store command, if is null
     *              value is taken as an argument, otherwise
     *              value is taken as an option
     * @param value value of argument or option
     */
    protected void addOption(String key, String value) {
        if (key == null) {
            key = DEFAULT_ARGS;
        }

        if (key.isBlank() || value.isBlank()) {
            return;
        }

        OPTIONS.put(key.strip(), value.strip());
    }
}
