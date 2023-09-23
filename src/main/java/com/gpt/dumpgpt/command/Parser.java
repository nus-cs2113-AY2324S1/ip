package com.gpt.dumpgpt.command;

import java.util.ArrayList;

public class Parser {
    private static final String OPTION_PREFIX = "/";
    private static final String TOKEN_DELIM = " ";

    public static Command parse(String userInput) {
        Command command = new Command();
        command.setOriginalInput(userInput);

        String[] tokens = getTokens(command);
        setCommandVerb(command, tokens);
        collectArgumentsAndOptions(command, tokens);
        return command;
    }

    private static String[] getTokens(Command command) {
        return command.getOriginalInput().split(TOKEN_DELIM);
    }

    private static void setCommandVerb(Command command, String[] tokens) {
        if (!command.isEmpty()) {
            String commandVerb = tokens[0].strip().toLowerCase();
            command.setCommandVerb(commandVerb);
        }
    }

    private static void collectArgumentsAndOptions(Command command, String[] tokens) {
        if (command.isEmpty()) {
            return;
        }

        String optionKey = null;
        ArrayList<String> currentTokens = new ArrayList<>();
        for (int i = 1; i < tokens.length; ++i) {
            String token = tokens[i];
            if (token.startsWith(OPTION_PREFIX)) {
                addOptionToCommand(command, optionKey, currentTokens);
                optionKey = token.substring(1);
                currentTokens.clear();
                continue;
            }
            currentTokens.add(token);
        }

        addOptionToCommand(command, optionKey, currentTokens);
    }

    private static void addOptionToCommand(Command command, String optionKey, ArrayList<String> currentTokens) {
        String optionValue = String.join(TOKEN_DELIM, currentTokens);
        command.addOption(optionKey, optionValue);
    }
}
