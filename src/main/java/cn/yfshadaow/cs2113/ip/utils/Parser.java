package cn.yfshadaow.cs2113.ip.utils;

import cn.yfshadaow.cs2113.ip.command.Command;

import java.util.*;

/**
 * Represents a parser to parse commands.
 */
public class Parser {

    /**
     * Parse command from string.
     *
     * @param s the string to be parsed
     * @return the command parsed
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static Command parseCommand(String s) throws IllegalArgumentException{
        Iterator<String> iterator = Arrays.stream(s.split(" ")).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Command cannot be empty!");
        }
        String cmdString = iterator.next();
        List<String> arguments = new ArrayList<>();
        Map<String, String> extraArguments = new HashMap<>();

        String currentTargetKey = null;
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.startsWith("-")) {
                if (next.length() == 1) {
                    throw new IllegalArgumentException("Extra argument identifier cannot be empty!");
                }
                currentTargetKey = next.substring(1);
                if (extraArguments.containsKey(currentTargetKey)) {
                    throw new IllegalArgumentException("Duplicate argument identifier!");
                }
                extraArguments.put(currentTargetKey, "");
            } else {
                if (currentTargetKey == null) {
                    arguments.add(next);
                } else {
                    String storedString = extraArguments.get(currentTargetKey);
                    extraArguments.put(currentTargetKey,  storedString + (storedString.isEmpty()? "" : " ") + next);
                }
            }
        }
        return new Command(cmdString, arguments, extraArguments);
    }
}
